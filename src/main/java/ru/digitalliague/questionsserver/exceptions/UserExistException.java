package ru.digitalliague.questionsserver.exceptions;

public class UserExistException extends RuntimeException{

    public UserExistException(String username) {
        super("User with username " + username + " already exist. Please check credentials");
    }
}
