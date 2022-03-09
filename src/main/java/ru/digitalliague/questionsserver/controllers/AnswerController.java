package ru.digitalliague.questionsserver.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.digitalliague.questionsserver.hateoas.AnswerModelAssembler;
import ru.digitalliague.questionsserver.modelDTO.AnswerDto;
import ru.digitalliague.questionsserver.modelDTO.View;
import ru.digitalliague.questionsserver.repositories.AnswerRepository;
import ru.digitalliague.questionsserver.service.AnswerService;
import ru.digitalliague.questionsserver.util.Mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/answers")
@AllArgsConstructor
public class AnswerController {

    private AnswerRepository answerRepository;
    private AnswerModelAssembler assembler;
    private Mapper mapper;
    private AnswerService service;

    @GetMapping("/get/{id}")
    public ResponseEntity<EntityModel<AnswerDto>> getAnswerById(@PathVariable Long id){
        AnswerDto answerDto = service.getAnswerById(id);
        return ResponseEntity.ok().body(assembler.toModel(answerDto));
    }
    @GetMapping(value = "/all")
    public ResponseEntity<List<EntityModel<AnswerDto>>> getAllAnswers(){
        List<EntityModel<AnswerDto>> allAnswers = service.getAllAnswers();
        return ResponseEntity.ok().body(allAnswers);
    }

    @PutMapping(value = "/update/{id}")
    @JsonView(value = View.AnswerPut.class)
    public ResponseEntity<String> updateAnswer(@RequestBody AnswerDto answerDto,@PathVariable Long id){
       service.updateAnswer(answerDto,id);
        return ResponseEntity.ok().body("Answer with id " + id + " updated successfully");
    }

    @PostMapping("/add")
    @JsonView(View.Answer.class)
    public ResponseEntity<String> addAnswer(@RequestBody AnswerDto answerDto){
        service.saveAnswer(answerDto);
        return ResponseEntity.ok().body("Answer updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.deleteAnswer(id);
        return ResponseEntity.ok().body("Answer with id " + id + " delete successfully");
    }

}
