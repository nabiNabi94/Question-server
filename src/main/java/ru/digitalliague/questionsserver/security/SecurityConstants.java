package ru.digitalliague.questionsserver.security;

public interface SecurityConstants {
    String SIGN_UP_URLS = "/api/auth/**";
    String SECRET = "SecretKeyGenJWT";
    String HEADERS_STRING = "Authorization";
    String CONTENT_TYPE = "application/json";
    String TOKEN_PREFIX = "Bearer ";
    long EXPiRATION_TIME = 600_000;

}
