package ru.digitalliague.questionsserver.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.EntityGraph;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "profiles")
@NamedEntityGraphs(value = {
        @NamedEntityGraph(
                name = "profile.fields",
                attributeNodes = {
                        @NamedAttributeNode(value = "id"),
                        @NamedAttributeNode(value = "name"),
                }
        )
})
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prof_id")
    private Long id;
    @Column(name = "prof_name")
    private String name;

    @OneToMany(mappedBy = "profile")
    @JsonManagedReference(value = "question-profile")
    private List<Question> questions;

    public void addTestToProfile(Question question){
        if (questions == null) questions = new ArrayList<>();
        question.setProfile(this);
        questions.add(question);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(id, profile.id) && Objects.equals(name, profile.name) && Objects.equals(questions, profile.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, questions);
    }
}
