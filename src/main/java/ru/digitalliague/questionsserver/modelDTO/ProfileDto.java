package ru.digitalliague.questionsserver.modelDTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

public class ProfileDto {
    @JsonView(View.Normal.class)
    private String name;

    @JsonBackReference(value = "question-profile")
    @JsonView(View.Profile.class)
    private List<QuestionDto> questions;


    public ProfileDto() {
    }

    public ProfileDto(String name, List<QuestionDto> questions) {
        this.name = name;
        this.questions = questions;
    }

    public List<QuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDto> questions) {
        this.questions = questions;
    }

    public ProfileDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @Override
//    public String toString() {
//        return "ProfileDto{" +
//                "name='" + name + '\'' +
//                '}';
//    }
}
