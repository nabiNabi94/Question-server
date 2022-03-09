package ru.digitalliague.questionsserver.excaptions;

public class AnswerNotFoundException extends RuntimeException{

    public AnswerNotFoundException(Long id) {
        super("Answer with id " + id + " not funded");
    }
    public AnswerNotFoundException() {
        super("Answers not funded");
    }
}
