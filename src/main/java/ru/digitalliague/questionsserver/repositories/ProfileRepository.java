package ru.digitalliague.questionsserver.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.digitalliague.questionsserver.entity.Profile;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile,Long> {

    @Override
    @EntityGraph(value = "profile.fields")
    List<Profile> findAll();

}
