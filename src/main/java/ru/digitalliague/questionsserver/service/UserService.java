package ru.digitalliague.questionsserver.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.digitalliague.questionsserver.entity.User;
import ru.digitalliague.questionsserver.entity.enums.ERoles;
import ru.digitalliague.questionsserver.exceptions.UserExistException;
import ru.digitalliague.questionsserver.exceptions.UserNotFoundException;
import ru.digitalliague.questionsserver.modelDTO.UserDto;
import ru.digitalliague.questionsserver.payload.request.SignupRequest;
import ru.digitalliague.questionsserver.repositories.UserRepository;
import ru.digitalliague.questionsserver.util.Mapper;

import java.security.Principal;

@Service
@AllArgsConstructor
public class UserService {

    private final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final Mapper mapper;

    public User createUser(SignupRequest userIn){
        User user = mapper.mapping(userIn, User.class);
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.getRoles().add(ERoles.ROLE_USER);
        try {
            LOG.info("Saving user {}",userIn.getEmail());
           return userRepository.save(user);
        }catch (Exception e){
            LOG.error("Error during saving {}",e.getMessage());
            throw new UserExistException(userIn.getUsername());
        }
    }

    public User updateUser(UserDto userDto, Principal principal){
        User user = getUserByPrincipal(principal);
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());

        return userRepository.save(user);
    }
    public User getCurrentUser(Principal principal){
        return getUserByPrincipal(principal);
    }


    public User getUserByPrincipal(Principal principal){
        String username = principal.getName();
       return userRepository.findUserByUsername(username)
               .orElseThrow(() -> new UserNotFoundException(username));
    }


}
