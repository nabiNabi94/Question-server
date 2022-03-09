package ru.digitalliague.questionsserver.excaptions;

import org.w3c.dom.ranges.RangeException;

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
