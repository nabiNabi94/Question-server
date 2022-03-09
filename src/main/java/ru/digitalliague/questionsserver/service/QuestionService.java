package ru.digitalliague.questionsserver.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.digitalliague.questionsserver.excaptions.MismatchOfCorrectAnswersException;
import ru.digitalliague.questionsserver.excaptions.QuestionAlreadyExistsException;
import ru.digitalliague.questionsserver.excaptions.QuestionNotFoundException;
import ru.digitalliague.questionsserver.modelDTO.AnswerDto;
import ru.digitalliague.questionsserver.modelDTO.QuestionDto;
import ru.digitalliague.questionsserver.repositories.QuestionRepository;
import ru.digitalliague.questionsserver.util.Mapper;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
@Transactional
public class QuestionService {

   private QuestionRepository repository;
   private AnswerService answerService;
   private Mapper mapper;

    public void updateQuestionInDB(QuestionDto qDto, Long id) {
        repository.findById(id).map(q ->
                repository.updateQuestion(
                        qDto.getName(),
                        qDto.getNumOfCorr(),
                        id,
                        qDto.getLevel().getName(),
                        qDto.getProfile().getName()))
                .orElseThrow(() -> new QuestionNotFoundException(id));
    }

    public void createQuestionDto(QuestionDto que){
       if (repository.existsByNameAllIgnoreCase(que.getName()).get())
           throw new QuestionAlreadyExistsException(que);
        long count = Stream.of(que)
                .flatMap(q -> q.getAnswers().stream())
                .filter(AnswerDto::getCheck).count();
        if (count != que.getNumOfCorr())
            throw new MismatchOfCorrectAnswersException(que.getNumOfCorr(),count);
        repository.saveQuestion(que.getName(),que.getNumOfCorr(),que.getLevel().getName(),que.getProfile().getName());
        answerService.saveAnswerToQuestion(que);
    }
}
