package ru.digitalliague.questionsserver.exceptions;

import ru.digitalliague.questionsserver.modelDTO.QuestionDto;

public class QuestionAlreadyExistsException extends RuntimeException{

    public QuestionAlreadyExistsException(QuestionDto questionDto) {
        super("Question with name" +
                questionDto.getName() +
                " and numbers questions " +
                questionDto.getNumOfCorr() +
                " already exists");
    }
}
