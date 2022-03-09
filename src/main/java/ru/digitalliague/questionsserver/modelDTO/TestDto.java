package ru.digitalliague.questionsserver.modelDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.Valid;
import java.util.List;


public class TestDto {

    @JsonIgnore
    private Long tst_id;
    @Valid
    private List<QuestionDto> questions;
    @Valid
    private ProfileDto profile;
    @Valid
    private LevelDto level;


    public TestDto(Long tst_id, List<QuestionDto> questions, Long questId, ProfileDto profile, LevelDto level) {
        this.questions = questions;
        this.profile = profile;
        this.level = level;
        this.tst_id = tst_id;
    }
    public TestDto() {
    }

    public Long getTst_id() {
        return tst_id;
    }

    public void setTst_id(Long tst_id) {
        this.tst_id = tst_id;
    }

    public List<QuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDto> questions) {
        this.questions = questions;
    }


    public ProfileDto getProfile() {
        return profile;
    }

    public void setProfile(ProfileDto profile) {
        this.profile = profile;
    }

    public LevelDto getLevel() {
        return level;
    }

    public void setLevel(LevelDto level) {
        this.level = level;
    }

//    @Override
//    public String toString() {
//        return "TestDto{" +
//                "questions=" + questions +
//                ", profile=" + profile +
//                ", level=" + level +
//                '}';
//    }
}
