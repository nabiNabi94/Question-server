package ru.digitalliague.questionsserver.advice;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.digitalliague.questionsserver.exceptions.AnswerNotFoundException;

@ControllerAdvice
public class AnswerExceptionAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AnswerNotFoundException.class)
    public String answerNotFoundHandler(AnswerNotFoundException ex){
        return ex.getMessage();
    }
}
