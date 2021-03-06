package avito.converter.repository;

import avito.converter.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;


    public User createNewUser(String alias){
        User user = new User();
        user.setAlias(alias);
        log.info("Created new user with name {}",alias);
        userRepository.save(user);
        return user;
    }

    public List<String> getAllUsersAlias(){
        return userRepository.findAllAlias();
    }


    public User authenticateUser(String alias){
        Optional<User> userOpt = userRepository.findByAlias(alias);
        log.info("Authenticate {}",alias);
        return userOpt.orElseGet(() -> createNewUser(alias));
    }

    public void updateUserData(User user){
        userRepository.save(user);
    }
}
