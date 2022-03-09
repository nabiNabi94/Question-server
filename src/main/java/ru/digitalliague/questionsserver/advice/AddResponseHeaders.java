package ru.digitalliague.questionsserver.advice;

import org.springframework.http.HttpHeaders;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@WebFilter("/*")
public class AddResponseHeaders implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter gmt = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH).withZone(ZoneId.of("GMT"));
        response.addHeader(HttpHeaders.DATE,time.format(gmt));
        filterChain.doFilter(servletRequest,response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
