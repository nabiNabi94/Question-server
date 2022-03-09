package ru.digitalliague.questionsserver.modelDTO;


import com.fasterxml.jackson.annotation.*;
import liquibase.pro.packaged.V;

import java.util.List;
import java.util.Objects;

public class QuestionDto {

    @JsonIgnore
    private Long id;
    @JsonView(value = View.QuestionName.class)
    private String name;
    @JsonView(value = View.Normal.class)
    private Long numOfCorr;
    @JsonManagedReference(value = "question-level")
    @JsonView(value = View.Normal.class)
    private LevelDto level;
    @JsonManagedReference(value = "question-profile")
    @JsonView(value = View.Normal.class)
    private ProfileDto profile;
    @JsonManagedReference(value = "question-answer")
    @JsonView(value = View.Get.class)
    private List<AnswerDto> answers;

    public QuestionDto() {}

    public QuestionDto( String name, Long numOfCorr, LevelDto level, ProfileDto profile, List<AnswerDto> answers) {
        this.name = name;
        this.numOfCorr = numOfCorr;
        this.level = level;
        this.profile = profile;
        this.answers = answers;
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

    public Long getNumOfCorr() {
        return numOfCorr;
    }

    public void setNumOfCorr(Long numOfCorr) {
        this.numOfCorr = numOfCorr;
    }

    public LevelDto getLevel() {
        return level;
    }

    public void setLevel(LevelDto level) {
        this.level = level;
    }

    public ProfileDto getProfile() {
        return profile;
    }

    public void setProfile(ProfileDto profile) {
        this.profile = profile;
    }

    public List<AnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDto> answers) {
        this.answers = answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionDto that = (QuestionDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(numOfCorr, that.numOfCorr) && Objects.equals(level, that.level) && Objects.equals(profile, that.profile) && Objects.equals(answers, that.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, numOfCorr, level, profile, answers);
    }

//    @Override
//    public String toString() {
//        return "QuestionDto{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", numOfCorr=" + numOfCorr +
//                ", level=" + level +
//                ", profile=" + profile +
//                ", answers=" + answers +
//                '}';
//    }
}
