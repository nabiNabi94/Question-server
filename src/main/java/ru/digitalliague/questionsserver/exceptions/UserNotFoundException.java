package ru.digitalliague.questionsserver.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Long id) {
        super("User with id " + id + " not funded");
    }
    public UserNotFoundException(String name) {
        super("User with name " + name + " not funded");
    }
    public UserNotFoundException() {
        super("User not funded");
    }
}
