package ru.digitalliague.questionsserver.exceptions;

public class QuestionNotFoundException extends RuntimeException {

    public QuestionNotFoundException(Long id) {
        super("Cloud not find question with id " + id);
    }
    public QuestionNotFoundException(String name) {
        super("Cloud not find question with name " + name);
    }
    public QuestionNotFoundException() {
        super("Cloud not find question with ");
    }
}
