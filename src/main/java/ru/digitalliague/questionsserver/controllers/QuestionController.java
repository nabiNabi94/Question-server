package ru.digitalliague.questionsserver.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.digitalliague.questionsserver.entity.Question;
import ru.digitalliague.questionsserver.excaptions.QuestionNotFoundException;
import ru.digitalliague.questionsserver.hateoas.QuestionModelAssembler;
import ru.digitalliague.questionsserver.modelDTO.QuestionDto;
import ru.digitalliague.questionsserver.modelDTO.View;
import ru.digitalliague.questionsserver.repositories.QuestionRepository;
import ru.digitalliague.questionsserver.service.QuestionService;
import ru.digitalliague.questionsserver.util.Mapper;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/question")
@AllArgsConstructor
@Validated
public class QuestionController {

    private QuestionRepository repository;
    private QuestionModelAssembler assembler;
    private Mapper mapper;
    private QuestionService questionService;


    @GetMapping("/get/{id}")
    @JsonView(View.Get.class)
    public EntityModel<QuestionDto> getQuestionById(@PathVariable Long id) {
        Question question = repository.findById(id).orElseThrow(() -> new QuestionNotFoundException(id));
        QuestionDto mapping = mapper.mapping(question, QuestionDto.class);
        return assembler.toModel(mapping);
    }

    @GetMapping("/all")
    @JsonView(value = View.Get.class)
    public List<EntityModel<QuestionDto>> getAllQuestions() {
        long l = System.currentTimeMillis();
        List<Question> allQuestions = repository.findAll();
        if (allQuestions.isEmpty()) {
            throw new QuestionNotFoundException();
        }
        System.out.println((double) System.currentTimeMillis() - l);
        return mapper.mapping(allQuestions, QuestionDto.class)
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
    }

    @JsonView(value = View.Get.class)
    @PostMapping("/create")
    public ResponseEntity<String> createNewQuestion(@Valid @RequestBody QuestionDto questionDto) {
        questionService.createQuestionDto(questionDto);
        return ResponseEntity.ok().body("Question created successfully");

    }

    @JsonView(value = View.Update.class)
    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<String> updateQuestion(@RequestBody QuestionDto questionDto,
                                                                   @PathVariable Long id) {
        questionService.updateQuestionInDB(questionDto, id);
        return ResponseEntity
                        .ok()
                        .body("Question updated successfully");
    }


    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<String> deleteQuestionById(@PathVariable Long id) {
        repository.deleteQuestionById(id).orElseThrow(() -> new QuestionNotFoundException(id));
        return ResponseEntity
                        .ok()
                        .body("Question with id " + id + " deleted successfully");
    }




}
