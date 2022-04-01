package ru.digitalliague.questionsserver.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.digitalliague.questionsserver.security.JWTTokenProvider;

@Data
@AllArgsConstructor
public class JWTTokenSuccessResponse {
    private boolean isSuccess;
    private String jwt;


}
