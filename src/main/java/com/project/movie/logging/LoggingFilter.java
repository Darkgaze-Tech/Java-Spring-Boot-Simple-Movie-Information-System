package com.project.movie.logging;

import com.project.movie.service.LoggingSvc;
import com.project.movie.util.StatusExposingServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@Component
@Slf4j
public class LoggingFilter extends OncePerRequestFilter {

    @Autowired
    private LoggingSvc loggingSvc;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        filterChain.doFilter(requestWrapper, responseWrapper);

        String responseBody = getStringValue(responseWrapper.getContentAsByteArray());
        String requestBody = getStringValue(requestWrapper.getContentAsByteArray());
        log.info("response body " + responseBody);
        log.info("request body " + requestBody);
        HashMap<String, Object> map = new HashMap<>();
        map.put("method", requestWrapper.getMethod());
        map.put("json_request", requestBody);
        map.put("json_response", responseBody);
        map.put("uri", requestWrapper.getRequestURI());
        map.put("http_status", String.valueOf(responseWrapper.getStatus()   ));
        loggingSvc.createLog(map, "api-log");
        responseWrapper.copyBodyToResponse();
    }

    private String getStringValue(byte[] contentAsByteArray) {
        try {
            return new String(contentAsByteArray, StandardCharsets.UTF_8);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}

