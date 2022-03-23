package ru.digitalliague.questionsserver.modelDTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;


public class AnswerDto {

    @JsonIgnore
    private Long id;
    @JsonView(value = View.AnswerPut.class)
    private String name;
    @JsonBackReference(value = "question-answer")
    @JsonView(value = View.Answer.class)
    private QuestionDto question;
    @JsonView(value = View.AnswerPut.class)
    private Boolean check;

    public AnswerDto() {
    }

    public AnswerDto(Long id, String name, QuestionDto question, Boolean check) {
        this.id = id;
        this.name = name;
        this.question = question;
        this.check = check;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public QuestionDto getQuestion() {
        return question;
    }

    public void setQuestion(QuestionDto question) {
        this.question = question;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

//    @Override
//    public String toString() {
//        return "AnswerDto{" +
//                "name='" + name + '\'' +
//                ", question=" + question +
//                ", check=" + check +
//                '}';
//    }
}
