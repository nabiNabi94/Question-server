package ru.digitalliague.questionsserver.exceptions;

public class ProfileNotFoundException extends RuntimeException{
    public ProfileNotFoundException() {
        super("Answers not funded");
    }
}
