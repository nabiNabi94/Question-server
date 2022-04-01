package ru.digitalliague.questionsserver.security;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.digitalliague.questionsserver.entity.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTTokenProvider {
    private final Logger LOG = LoggerFactory.getLogger(JWTTokenProvider.class);

    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Date begin = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(begin.getTime() + SecurityConstants.EXPiRATION_TIME);

        String userId = Long.toString(user.getId());
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("id", userId);
        claimsMap.put("username", user.getEmail());
        claimsMap.put("name", user.getName());
        return Jwts
                .builder()
                .setSubject(userId)
                .setClaims(claimsMap)
                .setIssuedAt(begin)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET)
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException |
                MalformedJwtException |
                ExpiredJwtException |
                UnsupportedJwtException |
                IllegalArgumentException ex) {
            LOG.error(ex.getMessage());
            return false;
        }
    }

    public Long getUserIdFromToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.SECRET)
                .parseClaimsJws(token)
                .getBody();
        String id = (String)claims.get("id");
        return Long.parseLong(id);
    }
}
