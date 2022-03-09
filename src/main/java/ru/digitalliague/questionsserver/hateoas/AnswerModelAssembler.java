package ru.digitalliague.questionsserver.hateoas;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ru.digitalliague.questionsserver.controllers.AnswerController;
import ru.digitalliague.questionsserver.modelDTO.AnswerDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class AnswerModelAssembler implements RepresentationModelAssembler<AnswerDto, EntityModel<AnswerDto>> {
    @Override
    public EntityModel<AnswerDto> toModel(AnswerDto entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(AnswerController.class).getAnswerById(entity.getId())).withRel("get answer by id"),
                linkTo(methodOn(AnswerController.class).getAllAnswers()).withRel("get all answer"),
                linkTo(methodOn(AnswerController.class).addAnswer(entity)).withRel("add answer"),
                linkTo(methodOn(AnswerController.class).updateAnswer(entity,entity.getId())).withRel("update answer by id"),
                linkTo(methodOn(AnswerController.class).delete(entity.getId())).withRel("delete answer by id"));
    }
}
