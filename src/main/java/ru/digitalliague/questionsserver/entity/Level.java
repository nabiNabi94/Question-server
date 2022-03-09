package ru.digitalliague.questionsserver.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "levels")
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lvl_id")
    private Long id;
    @Column(name = "lvl_name")
    private String name;

    @OneToMany(mappedBy = "profile")
   @JsonBackReference(value = "question-level")
    private List<Question> questions;

    public void addTestToProfile(Question question){
        if (questions == null) questions = new ArrayList<>();
        question.setLevel(this);
        questions.add(question);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Level level = (Level) o;
        return Objects.equals(id, level.id) && Objects.equals(name, level.name) && Objects.equals(questions, level.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, questions);
    }
}
