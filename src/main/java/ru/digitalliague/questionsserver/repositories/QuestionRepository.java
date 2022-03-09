package ru.digitalliague.questionsserver.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.digitalliague.questionsserver.entity.Question;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Optional<Boolean> existsByNameAllIgnoreCase(String name);

    @Modifying
    @Query(value = "insert into questions (que_name, num_of_corr, que_lvl_id,que_prof_id)  values (:name,:num,(select lvl_id from levels where lvl_name = :lvlName )," +
            "(select prof_id from profiles where prof_name = :profName )) ", nativeQuery = true)
    void saveQuestion(@Param("name") String name, @Param("num") Long num, @Param("lvlName") String lvlName, @Param("profName") String profName);

    @Modifying
    @Query(value = "update questions set que_name = :name," +
            "num_of_corr = :num," +
            " que_lvl_id = (select lvl_id from levels where lvl_name = :level)," +
            "que_prof_id = (select prof_id from profiles where prof_name = :profile) where que_id = :id"
            , nativeQuery = true)
    Integer updateQuestion(@Param("name") String name,
                           @Param("num") Long num,
                           @Param("id") Long id,
                           @Param("level") String level,
                           @Param("profile") String profile);

    @EntityGraph(value = "question.all.fields")
    Optional<Long> deleteQuestionById(@Param("id") Long id);


    @EntityGraph(value = "question.all.fields", type = EntityGraph.EntityGraphType.FETCH)
    List<Question> findAll();

    @EntityGraph(value = "question.all.fields", type = EntityGraph.EntityGraphType.FETCH)
    Optional<Question> findById(Long id);


    Optional<Question> findQuestionByName(String name);

    @Modifying
    @Query(nativeQuery = true,value = "update questions set num_of_corr = :newNumOfCorr where que_id = :id")
    void updateQuestionNumOfCorr(@Param("newNumOfCorr") Long newNumOfCorr,@Param("id") Long id);

}
