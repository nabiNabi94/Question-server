package ru.digitalliague.questionsserver.advice;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.digitalliague.questionsserver.controllers.AnswerController;
import ru.digitalliague.questionsserver.excaptions.AnswerNotFoundException;
import ru.digitalliague.questionsserver.hateoas.QuestionModelAssembler;

@ControllerAdvice
public class AnswerExceptionAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AnswerNotFoundException.class)
    public String answerNotFoundHandler(AnswerNotFoundException ex){
        return ex.getMessage();
    }
}
