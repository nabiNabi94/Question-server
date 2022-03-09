package ru.digitalliague.questionsserver.service;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import ru.digitalliague.questionsserver.entity.Answer;
import ru.digitalliague.questionsserver.entity.Question;
import ru.digitalliague.questionsserver.excaptions.AnswerNotFoundException;
import ru.digitalliague.questionsserver.excaptions.QuestionNotFoundException;
import ru.digitalliague.questionsserver.hateoas.AnswerModelAssembler;
import ru.digitalliague.questionsserver.modelDTO.AnswerDto;
import ru.digitalliague.questionsserver.modelDTO.QuestionDto;
import ru.digitalliague.questionsserver.repositories.AnswerRepository;
import ru.digitalliague.questionsserver.repositories.QuestionRepository;
import ru.digitalliague.questionsserver.util.Mapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class AnswerService {

    private AnswerRepository repository;
    private QuestionRepository questionRepository;
    private Mapper mapper;
    private AnswerModelAssembler assembler;




    public AnswerDto getAnswerById(Long id){
        Answer answer = repository.findByAnsId(id).orElseThrow(() -> new AnswerNotFoundException(id));
        return mapper.mapping(answer, AnswerDto.class);
    }

    public List<EntityModel<AnswerDto>> getAllAnswers(){
        List<Answer> all = repository.findAll();
        if (all.isEmpty()) throw new AnswerNotFoundException();
        List<AnswerDto> mapping = mapper.mapping(all, AnswerDto.class);
        return mapping.stream().map(assembler::toModel).collect(Collectors.toList());
    }

    public void updateAnswer(AnswerDto answerDto,Long id){
        Answer answer = repository.findById(id).orElseThrow(() -> new AnswerNotFoundException(id));
        chekUpdateAnswer(answerDto,answer);
        answer.setName(answerDto.getName());
        answer.setCheck(answerDto.getCheck());
        repository.save(answer);
    }

    public void saveAnswer(AnswerDto answer) {
        String questionName = answer.getQuestion().getName();
        Question question = questionRepository
                .findQuestionByName(questionName)
                .orElseThrow(() -> new QuestionNotFoundException(questionName));
        if (answer.getCheck()) incrementNumOfCorrToQuestion(question);
        repository.saveAnswers(answer.getName(), questionName, answer.getCheck());

    }

    public void saveAnswerToQuestion(QuestionDto question) {
        Stream.of(question)
                .flatMap(q -> q.getAnswers().stream())
                .forEach(a -> repository.saveAnswers(a.getName(), question.getName(), a.getCheck()));
    }

    public void deleteAnswer(Long id) {
        Answer answer = repository.findById(id).orElseThrow(() -> new QuestionNotFoundException(id));
        if (answer.getCheck()) incrementNumOfCorrToQuestion(answer.getQuestion());
        repository.deleteById(id);
    }

    public void incrementNumOfCorrToQuestion(Question question){
        questionRepository
                .updateQuestionNumOfCorr(
                        question.getNumOfCorr() + 1,question.getId());
    }
    public void decrementNumOfCorrToQuestion(Question question){
        questionRepository
                .updateQuestionNumOfCorr(
                        question.getNumOfCorr() - 1,question.getId());
    }
    public void chekUpdateAnswer(AnswerDto answerDto,Answer answer){
        if (answer.getCheck() && answer.getCheck() != answerDto.getCheck())
            decrementNumOfCorrToQuestion(answer.getQuestion());
        else if (!answer.getCheck() && answer.getCheck() != answerDto.getCheck())
            incrementNumOfCorrToQuestion(answer.getQuestion());
    }

}
