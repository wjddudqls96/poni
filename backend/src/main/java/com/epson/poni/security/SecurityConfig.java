package com.epson.poni.security;


import com.epson.poni.repository.UserRepository;
import com.epson.poni.security.filter.TokenAuthenticationFilter;
import com.epson.poni.security.jwt.HeaderTokenExtractor;
import com.epson.poni.security.jwt.JwtTokenUtils;
import com.epson.poni.service.user.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
                    .csrf((csrfConfig) -> csrfConfig.disable())
                    .headers(
                            (headerConfig) -> headerConfig.frameOptions(
                                    frameOptionsConfig -> frameOptionsConfig.disable()
                            )
                    )
//                    .authorizeHttpRequests((authorizeRequest) -> authorizeRequest
//                            .requestMatchers("/posts/new", "/comments/save").hasRole(Role.USER.name())
//                            .requestMatchers("/", "/css/**", "images/**", "/js/**", "/login/*", "/logout/*", "/posts/**", "/comments/**").permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/auth/success"), new AntPathRequestMatcher("/")).permitAll()
//                            .anyRequest().authenticated()
//                    )

                        .authorizeHttpRequests(request ->
                                request.requestMatchers(
                                        new AntPathRequestMatcher("/"),
                                        new AntPathRequestMatcher("/login/*"),
                                                new AntPathRequestMatcher("/logout/*"),
                                                new AntPathRequestMatcher("/css/**"),
                                                new AntPathRequestMatcher("images/**"),
                                                new AntPathRequestMatcher("/js/**")
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

//    JwtAuthFilter jwtAuthFilter(AuthenticationManager authenticationManager){
//        List<Path> skipPathList = new ArrayList<>();
////        skipPathList.add(new Path(HttpMethod.POST, "/user/login"));
////        skipPathList.add(new Path(HttpMethod.POST, "/user"));
////        skipPathList.add(new Path(HttpMethod.POST, "/api/pdf/**"));
////        skipPathList.add(new Path(HttpMethod.GET, "/test/**"));
////        skipPathList.add(new Path(HttpMethod.POST, "/api/v1/worksheet/**"));
////        skipPathList.add(new Path(HttpMethod.POST, "/api/scan/**"));
////        skipPathList.add(new Path(HttpMethod.POST, "/api/print/**"));
//        skipPathList.add(new Path(HttpMethod.GET, "/"));
////        skipPathList.add(new Path(HttpMethod.POST, "/**"));
//
//        FilterSkipMatcher matcher = new FilterSkipMatcher(skipPathList, "/**");
//        JwtAuthFilter filter = new JwtAuthFilter(matcher, extractor);
//        filter.setAuthenticationManager(authenticationManager);
//        return filter;
//    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }


//    @Bean
//    public BCryptPasswordEncoder encodePassword(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.addAllowedOrigin("http://localhost5173"); // local 테스트 시
//        configuration.setAllowCredentials(true);
//        configuration.addAllowedMethod("*");
//        configuration.addAllowedHeader("*");
//        configuration.addExposedHeader("*");
//        configuration.addExposedHeader("Access_Token");
//        configuration.addExposedHeader("Refresh_Token");
//        configuration.addAllowedOriginPattern("*"); // 배포 전 모두 허용
//        val source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//
//        return source;
//    }

//    @Autowired
//    void registerProvider(AuthenticationManagerBuilder auth){
//        auth.authenticationProvider(new FormLoginAuthProvider(userDetailService, encodePassword()));
//        auth.authenticationProvider(new JWTAuthProvider(userRepository, jwtTokenUtils));
//    }

//    FormLoginFilter formLoginFilter(AuthenticationManager authenticationManager){
//        FormLoginFilter formLoginFilter = new FormLoginFilter(authenticationManager);
//        formLoginFilter.setFilterProcessesUrl("/user/login");
//        formLoginFilter.setAuthenticationSuccessHandler(new FormLoginSuccessHandler(jwtTokenUtils));
//        formLoginFilter.afterPropertiesSet();
//        return formLoginFilter;
//    }


//    public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
//        @Override
//        public void configure(HttpSecurity http) throws Exception {
//            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
//            http
//                    .addFilterBefore(formLoginFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class)
//                    .addFilterBefore(jwtAuthFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class);
//        }
//    }

}
