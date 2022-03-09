package ru.digitalliague.questionsserver.hateoas;


import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ru.digitalliague.questionsserver.controllers.AnswerController;
import ru.digitalliague.questionsserver.controllers.QuestionController;
import ru.digitalliague.questionsserver.modelDTO.QuestionDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class QuestionModelAssembler implements RepresentationModelAssembler<QuestionDto, EntityModel<QuestionDto>> {


    @Override
    public EntityModel<QuestionDto> toModel(QuestionDto entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(QuestionController.class).getQuestionById(entity.getId())).withRel("get question by id"),
                linkTo(methodOn(QuestionController.class).getAllQuestions()).withRel("get all questions"),
                linkTo(methodOn(QuestionController.class).createNewQuestion(entity)).withRel("add question"),
                linkTo(methodOn(QuestionController.class).updateQuestion(entity,entity.getId())).withRel("update question by id"),
                linkTo(methodOn(QuestionController.class).deleteQuestionById(entity.getId())).withRel("delete question by id")

        );
    }

}
