//package ru.digitalliague.questionsserver.entity;
//
//import lombok.*;
//import org.hibernate.Hibernate;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//@Entity
//@Getter
//@Setter
//@ToString
//@RequiredArgsConstructor
//@Table(name = "tests")
//@NamedEntityGraphs(value = {
//        @NamedEntityGraph(
//                name = "test.questions-levels-profiles",
//                attributeNodes = {
//                        @NamedAttributeNode(value = "questions" ,subgraph = "question.answer"),
//                        @NamedAttributeNode(value = "profile"),
//                        @NamedAttributeNode(value = "level")
//                },
//                subgraphs = {
//                        @NamedSubgraph(name = "question.answer",
//                                attributeNodes =
//                                @NamedAttributeNode("answers"))
//                }
//        )
//})
//public class Test implements Serializable {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "tst_id")
//    private Long id;
//
//    @OneToMany(mappedBy = "test",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    @ToString.Exclude
//    private List<Question> questions;
//
//    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
//    @JoinColumn(name = "test_prof_id")
//    private Profile profile;
//
//    @ManyToOne(fetch = FetchType.LAZY,cascade ={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
//    @JoinColumn(name = "test_lvl_id")
//    private Level level;
//
//    public void addQuestion(Question question){
//        if (questions == null){
//            questions = new ArrayList<>();
//        }
//        question.setTest(this);
//        questions.add(question);
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        Test test = (Test) o;
//        return id != null && Objects.equals(id, test.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
//}
