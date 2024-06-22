package com.epson.poni.security;


import com.epson.poni.repository.UserRepository;
import com.epson.poni.security.filter.TokenAuthenticationFilter;
import com.epson.poni.security.jwt.HeaderTokenExtractor;
import com.epson.poni.security.jwt.JwtTokenUtils;
import com.epson.poni.service.user.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserRepository userRepository;
    private final JwtTokenUtils jwtTokenUtils;
    private final HeaderTokenExtractor extractor;
    private final CustomOAuth2UserService oAuth2UserService;
    private final FormLoginSuccessHandler formLoginSuccessHandler;
    private final TokenAuthenticationFilter tokenAuthenticationFilter;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() { // security를 적용하지 않을 리소스
        return web -> web.ignoring()
                // error endpoint를 열어줘야 함, favicon.ico 추가!
                .requestMatchers("/error", "/favicon.ico");
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                    .csrf((csrfConfig) -> csrfConfig.disable())
                    .headers(
                            (headerConfig) -> headerConfig.frameOptions(
                                    frameOptionsConfig -> frameOptionsConfig.disable()
                            )
                    )

                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(HttpMethod.POST, "/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/**").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/**").permitAll()
                        .requestMatchers(
                                new AntPathRequestMatcher("/"),
                                new AntPathRequestMatcher("/login/*"),
                                new AntPathRequestMatcher("/logout/*"),
                                new AntPathRequestMatcher("/css/**"),
                                new AntPathRequestMatcher("/images/**"),
                                new AntPathRequestMatcher("/js/**"),
                                new AntPathRequestMatcher("/api/v1/worksheet/cart")
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                    .logout(log -> log
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .deleteCookies("JSESSIONID", "remember-me") 	// 로그아웃 후 쿠키 삭제
                                    .invalidateHttpSession(true)
//                        .addLogoutHandler(logoutHandler())		 // 로그아웃 핸들러
//                        .logoutSuccessHandler(logoutSuccessHandler()	// 로그아웃 성공 후 핸들러
                    )

                    // oauth2 설정
                    .oauth2Login(oauth -> // OAuth2 로그인 기능에 대한 여러 설정의 진입점
                    // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정을 담당
                    oauth.userInfoEndpoint(c -> c.userService(oAuth2UserService))
                            // 로그인 성공 시 핸들러
                            .successHandler(formLoginSuccessHandler)
                    )

                    // jwt 관련 설정
                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

//                     인증 예외 핸들링
//                    .exceptionHandling((exceptions) -> exceptions
//                            .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
//                            .accessDeniedHandler(new CustomAccessDeniedHandler()));
        ;

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:5173");
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true); // 인증 정보 포함 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
