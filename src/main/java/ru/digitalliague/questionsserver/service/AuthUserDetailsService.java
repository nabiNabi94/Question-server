package ru.digitalliague.questionsserver.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.digitalliague.questionsserver.entity.User;
import ru.digitalliague.questionsserver.exceptions.UserNotFoundException;
import ru.digitalliague.questionsserver.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
        return build(user);
    }

    public User loadUserById(Long id){
        User user = repository.findUserById(id).orElseThrow(() -> new UserNotFoundException(id));
        return user;
    }

    public static User build(User user){
        List<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());

        return new User(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities);
    }

}
