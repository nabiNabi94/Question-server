package ru.digitalliague.questionsserver.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.digitalliague.questionsserver.exceptions.MismatchOfCorrectAnswersException;
import ru.digitalliague.questionsserver.exceptions.QuestionAlreadyExistsException;
import ru.digitalliague.questionsserver.exceptions.QuestionNotFoundException;

@ControllerAdvice
public class QuestionExceptionAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(QuestionNotFoundException.class)
    public String questionNotFoundHandler(QuestionNotFoundException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(QuestionAlreadyExistsException.class)
    public String questionAlreadyExistsException(QuestionAlreadyExistsException ex){
        return ex.getMessage();
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MismatchOfCorrectAnswersException.class)
    public String mismatchOfCorrectAnswersException(MismatchOfCorrectAnswersException ex){
        return ex.getMessage();
    }

}
