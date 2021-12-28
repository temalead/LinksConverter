package avito.converter.service;

import avito.converter.domain.PrettyUrl;
import avito.converter.domain.User;
import avito.converter.repository.PrettyUrlRepository;
import avito.converter.repository.UserRepository;
import avito.converter.repository.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ConverterServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PrettyUrlRepository prettyUrlRepository;
    @Mock
    private UserService userService;

    private ConverterServiceImpl converterService;

    @BeforeEach
    void setUp(){
        converterService=new ConverterServiceImpl(prettyUrlRepository,userRepository,userService);
    }

    @Test
    void createNewUrlFromOld() {

    }

    @Test
    void shouldAddNewUrlToUserUrlList(){
        //given
        User user = new User();
        PrettyUrl prettyUrl = new PrettyUrl();
        PrettyUrl prettyUrl1 = new PrettyUrl();
        user.getUrls().add(prettyUrl);
        user.getUrls().add(prettyUrl1);
        user.setId(1L);
        user.setAlias("user_0");
        User expectedUser=new User();
        expectedUser.setUrls(List.of(new PrettyUrl(),new PrettyUrl(),new PrettyUrl()));
        when(userRepository.findByAlias(any())).thenReturn(Optional.of(user));

        //when
        converterService.addUrlToUser(new PrettyUrl(), user.getAlias());

        //then
        then(user.getUrls().size()).isEqualTo(expectedUser.getUrls().size());
    }

}