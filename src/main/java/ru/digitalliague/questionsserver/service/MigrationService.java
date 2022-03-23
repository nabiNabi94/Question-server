package ru.digitalliague.questionsserver.service;

import org.springframework.stereotype.Service;
import ru.digitalliague.questionsserver.entity.Question;

@Service
public class MigrationService {

    public Question convertQuestion(Question question){
        return question;
    }

}
