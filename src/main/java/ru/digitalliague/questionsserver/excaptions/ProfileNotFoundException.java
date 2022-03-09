package ru.digitalliague.questionsserver.excaptions;

public class ProfileNotFoundException extends RuntimeException{
    public ProfileNotFoundException() {
        super("Answers not funded");
    }
}
