package org.example.gc_coffee.spring.config;

import org.example.gc_coffee.spring.filter.RoleFilter;
import org.example.gc_coffee.spring.handler.RoleAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private RoleFilter roleFilter;

    @Autowired
    private RoleAccessDeniedHandler roleAccessDeniedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.cors(AbstractHttpConfigurer::disable) // 추 후에 프론트와 연결할 때 프론트 서버 CORS 만 풀고 나머지는 불허해야 한다.
            .csrf(AbstractHttpConfigurer::disable) // cookie 등을 활용하지 않고 있어서 csrf 비활성화, 추 후에 csrf 공격을 막고싶으면 여기 활성화 하고 쿠키들의 설정을 해줘야한다.
            .httpBasic(AbstractHttpConfigurer::disable) // Basic 인증 환경이 아니고 jwt 토큰이니까 Basic 모드 비활성화
            .sessionManagement(AbstractHttpConfigurer::disable) // sessionless 기반 아님으로 비활성화
            .authorizeHttpRequests(req -> {
                // GET 요청은 USER와 ADMIN 모두 접근 가능
                req.requestMatchers(HttpMethod.GET, "/product/**").hasAnyRole("USER", "ADMIN");

                // POST, PUT, DELETE 요청은 ADMIN만 접근 가능
                req.requestMatchers(HttpMethod.POST, "/product/**").hasRole("ADMIN");
                req.requestMatchers(HttpMethod.PUT, "/product/**").hasRole("ADMIN");
                req.requestMatchers(HttpMethod.DELETE, "/product/**").hasRole("ADMIN");

                // 다른 요청들에 대한 권한 설정
                req.requestMatchers("/order/**").hasAnyRole("USER", "ADMIN");
                req.anyRequest().authenticated(); // 그 외 나머지 요청은 인증 필요
            })
            .addFilterAfter(roleFilter, CorsFilter.class)
            .exceptionHandling(exceptionHandling -> exceptionHandling
                    .accessDeniedHandler(roleAccessDeniedHandler) // 권한 오류 핸들러 등록
            );

        return http.build();
    }

}
