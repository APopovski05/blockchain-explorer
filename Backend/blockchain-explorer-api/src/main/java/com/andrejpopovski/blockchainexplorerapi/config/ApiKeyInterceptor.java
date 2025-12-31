package com.andrejpopovski.blockchainexplorerapi.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ApiKeyInterceptor implements HandlerInterceptor {

    @Value("${api.key.value}")
    private String validApiKey;

    @Value("${api.key.header}")
    private String apiKeyHeader;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        // Skip API key validation for H2 console
        if (request.getRequestURI().startsWith("/h2-console")) {
            return true;
        }

        // Allow CORS preflight requests
if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
    return true;
}


        String apiKey = request.getHeader(apiKeyHeader);

        if (apiKey == null || apiKey.trim().isEmpty()) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Missing API key in " + apiKeyHeader + " header\"}");
            return false;
        }

        if (!apiKey.equals(validApiKey)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Invalid API key\"}");
            return false;
        }

        return true;
    }
}
