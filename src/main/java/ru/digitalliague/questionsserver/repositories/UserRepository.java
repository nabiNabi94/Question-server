package ru.digitalliague.questionsserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.digitalliague.questionsserver.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByName(String name);
    Optional<User> findUserByUsername(String name);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserById(Long id);

}
