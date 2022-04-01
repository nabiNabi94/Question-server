package ru.digitalliague.questionsserver.security;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.digitalliague.questionsserver.entity.User;
import ru.digitalliague.questionsserver.service.AuthUserDetailsService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

@Service
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final Logger LOG = LoggerFactory.getLogger(JWTAuthenticationFilter.class);


    @Autowired
    private JWTTokenProvider jwtTokenProvider;
    @Autowired
    private AuthUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
    String jwt = getJwtTokenFromRequest(request);
    if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
        Long userIdFromToken = jwtTokenProvider.getUserIdFromToken(jwt);
        User userDetails = userDetailsService.loadUserById(userIdFromToken);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, Collections.emptyList());

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    }catch (Exception e){
            LOG.error("Cloud not set authentication");
        }

        filterChain.doFilter(request,response);

    }

    private String getJwtTokenFromRequest(HttpServletRequest request){
        String securityHeader = request.getHeader(SecurityConstants.HEADERS_STRING);
        if (StringUtils.hasText(securityHeader) &&
                securityHeader.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            return securityHeader.split(" ")[1];
        }
        return null;
    }


}
