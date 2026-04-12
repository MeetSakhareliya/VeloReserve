package com.distributed.reservation_system.filter;

import com.distributed.common.exception.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        String uri = (String) request.getAttribute("jakarta.servlet.forward.request_uri");
        if (uri == null) {
            uri = request.getRequestURI();
        }

        // 2. Build your error body
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("message", "Unauthorized request at: " + uri);
        errorDetails.put("status", 401);
        errorDetails.put("timestamp", System.currentTimeMillis());

        // 3. Write it to the response stream
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), errorDetails);
    }

}
