package ru.digitalliague.questionsserver.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.digitalliague.questionsserver.entity.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer,Long> {
    @EntityGraph(value = "answer.fields")
    Optional<Answer> findByAnsId(Long id);

    @Modifying
    @Query(value = "insert into answers (ans_name, ans_que_id, ans_check) VALUES (:name,(select que_id from questions where que_name = :queName),:check)",nativeQuery = true)
    void saveAnswers(@Param("name") String name,@Param("queName") String queName,@Param("check")Boolean check);

    @Override
    @EntityGraph("answer.fields")
    List<Answer> findAll();
}
