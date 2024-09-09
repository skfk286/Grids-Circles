package org.example.gc_coffee.spring.config;

import org.example.gc_coffee.spring.filter.RoleFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private RoleFilter roleFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.cors(AbstractHttpConfigurer::disable) // 추 후에 프론트와 연결할 때 프론트 서버 CORS 만 풀고 나머지는 불허해야 한다.
            .csrf(AbstractHttpConfigurer::disable) // cookie 등을 활용하지 않고 있어서 csrf 비활성화, 추 후에 csrf 공격을 막고싶으면 여기 활성화 하고 쿠키들의 설정을 해줘야한다.
            .httpBasic(AbstractHttpConfigurer::disable) // Basic 인증 환경이 아니고 jwt 토큰이니까 Basic 모드 비활성화
            .sessionManagement(AbstractHttpConfigurer::disable) // sessionless 기반 아님으로 비활성화
            .authorizeHttpRequests(req -> {
                req.requestMatchers("/product/**").hasRole("ADMIN"); // 관리자만 접근 가능
                req.requestMatchers("/order/**").hasAnyRole("ADMIN", "USER"); // 사용자만 접근 가능
                req.anyRequest().authenticated(); // 나머지는 인증 필요
            })
            .addFilterAfter(roleFilter, CorsFilter.class);

        return http.build();
    }

}
