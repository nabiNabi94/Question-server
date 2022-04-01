package ru.digitalliague.questionsserver.payload.response;

import lombok.Data;

@Data
public class MassageResponse {
    private String massage;
    public MassageResponse(String massage) {
        this.massage = massage;
    }
}
