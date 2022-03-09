package ru.digitalliague.questionsserver.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "answers")
@NamedEntityGraphs(value = {
        @NamedEntityGraph(name = "answer.fields",
                attributeNodes = {
                        @NamedAttributeNode(value = "name"),
                        @NamedAttributeNode(value = "check"),
                        @NamedAttributeNode(value = "question", subgraph = "question.graph")
                },
                subgraphs = {
                        @NamedSubgraph(name = "question.graph",
                                attributeNodes = {
                                        @NamedAttributeNode(value = "profile"),
                                        @NamedAttributeNode("level"),
                                        @NamedAttributeNode("name"),
                                        @NamedAttributeNode("numOfCorr")
                                })
                }
        )
})
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ans_id")
    private Long ansId;
    @Column(name = "ans_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ans_que_id")
    @JsonBackReference(value = "question-answer")
    private Question question;

    @Column(name = "ans_check")
    private Boolean check;

    public Answer(Long ansId, String name, Question question, Boolean check) {
        this.ansId = ansId;
        this.name = name;
        this.question = question;
        this.check = check;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Answer answer = (Answer) o;
        return ansId != null && Objects.equals(ansId, answer.ansId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
