package ru.digitalliague.questionsserver.excaptions;

public class MismatchOfCorrectAnswersException extends RuntimeException{

    public MismatchOfCorrectAnswersException(Long given, Long actual) {
        super("Mismatch between the number of correct answers in a question " + given + " and the number of correct answers " + actual);
    }
}
