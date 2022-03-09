package ru.digitalliague.questionsserver.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "questions")
@NamedEntityGraphs(value = {
        @NamedEntityGraph(name = "question.all.fields",
        attributeNodes = {
                @NamedAttributeNode(value = "profile", subgraph = "profile.name"),
                @NamedAttributeNode(value = "level",subgraph = "level.name"),
                @NamedAttributeNode(value = "answers",subgraph = "answer.fields")
        },
        subgraphs = {
                @NamedSubgraph(name = "profile.name",
                        attributeNodes = @NamedAttributeNode(value = "name")),
                @NamedSubgraph(name = "level.name",
                        attributeNodes = @NamedAttributeNode(value = "name")),
                @NamedSubgraph(name = "answer.fields",
                        attributeNodes = {
                        @NamedAttributeNode(value = "name"),
                        @NamedAttributeNode(value = "check")
                })
        }),
        @NamedEntityGraph(name = "question.id-name-numOfCorr",
        attributeNodes = {
                @NamedAttributeNode(value = "id"),
                @NamedAttributeNode(value = "name"),
                @NamedAttributeNode(value = "numOfCorr"),
        })
})
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "que_id")
    private Long id;
    @Column(name = "que_name")
    private String name;

    @Column(name = "num_of_corr")
    private Long numOfCorr;

    @ManyToOne
    @JoinColumn(name = "que_prof_id")
    @JsonBackReference(value = "question-profile")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "que_lvl_id")
    @JsonManagedReference(value = "question-level")
    private Level level;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "question-answer")
    private List<Answer> answers;

    public void addAnswer(Answer answer) {
        if (answers == null) {
            answers = new ArrayList<>();
        }
        answer.setQuestion(this);
        answers.add(answer);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) && Objects.equals(name, question.name) && Objects.equals(numOfCorr, question.numOfCorr) && Objects.equals(profile, question.profile) && Objects.equals(level, question.level) && Objects.equals(answers, question.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, numOfCorr, profile, level, answers);
    }
}
