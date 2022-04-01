package ru.digitalliague.questionsserver.advice;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.digitalliague.questionsserver.exceptions.UserExistException;
import ru.digitalliague.questionsserver.exceptions.UserNotFoundException;

@ControllerAdvice
public class UserExceptionAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public String userNotFoundHandler(UserNotFoundException ex){
        return ex.getMessage();
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserExistException.class)
    public String userNotFoundHandler(UserExistException ex){
        return ex.getMessage();
    }
//    @ResponseBody
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler({SignatureException.class,
//            MalformedJwtException.class,
//            ExpiredJwtException.class,
//            UnsupportedJwtException.class,
//            IllegalArgumentException.class})
//    public String userNotFoundHandler(Exception ex){
//        return ex.getMessage();
//    }




}
