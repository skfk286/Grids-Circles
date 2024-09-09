package org.example.gc_coffee.spring.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.gc_coffee.common.api.ApiResponse;
import org.example.gc_coffee.common.util.LoggerUtil;
import org.slf4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class RoleFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerUtil.getLogger();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String role = request.getHeader("Role");
        logger.info("Role : {}", role);

        if (role == null || (!role.equals("ADMIN") && !role.equals("USER"))) {
            // Role 값이 없거나 잘못된 경우, 에러 응답 반환
            setUnauthorizedResponse(response, "Role header is missing or invalid");
            return; // 필터 체인을 더 이상 진행하지 않음
        }

        // Role 값이 존재하는 경우, 인증 처리
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(null, null, Collections.singletonList(authority));
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }

    private void setUnauthorizedResponse(HttpServletResponse response, String message) throws IOException {
        // ApiResponse를 사용해 커스텀 JSON 응답 생성
        ApiResponse<String> apiResponse = new ApiResponse<>(false, message, null, HttpServletResponse.SC_UNAUTHORIZED);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(apiResponse);
        response.getWriter().write(jsonResponse);
    }
}
