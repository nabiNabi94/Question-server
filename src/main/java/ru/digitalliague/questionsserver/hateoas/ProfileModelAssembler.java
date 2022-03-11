package ru.digitalliague.questionsserver.hateoas;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Service;
import ru.digitalliague.questionsserver.controllers.ProfileController;
import ru.digitalliague.questionsserver.modelDTO.ProfileDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Service
public class ProfileModelAssembler implements RepresentationModelAssembler<ProfileDto, EntityModel<ProfileDto>> {

    @Override
    public EntityModel<ProfileDto> toModel(ProfileDto entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(ProfileController.class).addProfile(entity)).withRel("Add profile"),
                linkTo(methodOn(ProfileController.class).getAllProfile()).withRel("Get all profile"));
    }

    @Override
    public CollectionModel<EntityModel<ProfileDto>> toCollectionModel(Iterable<? extends ProfileDto> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
