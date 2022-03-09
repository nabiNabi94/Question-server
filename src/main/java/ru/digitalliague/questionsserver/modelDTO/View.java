package ru.digitalliague.questionsserver.modelDTO;

public interface View {
    interface Profile{}
    interface Level{}
    interface AnswerPut{}
    interface QuestionName{}
    class Answer implements AnswerPut,QuestionName{}
    class Normal implements AnswerPut {}
    class Update extends Normal implements QuestionName{}
    class Get extends Normal implements QuestionName{}

}
