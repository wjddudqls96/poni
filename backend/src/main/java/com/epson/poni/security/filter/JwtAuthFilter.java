//package com.epson.poni.security.filter;
//
//
//import com.epson.poni.security.jwt.HeaderTokenExtractor;
//import com.epson.poni.security.jwt.JwtPreProcessingToken;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//
//import java.io.IOException;
//
//@Slf4j
//public class JwtAuthFilter extends AbstractAuthenticationProcessingFilter {
////    private final RequestMatcher requiresAuthenticationRequestMatcher;
//    private final HeaderTokenExtractor extractor;
//
//    public JwtAuthFilter(
//            RequestMatcher requiresAuthenticationRequestMatcher,
//            HeaderTokenExtractor extractor
//    ) {
//        super(requiresAuthenticationRequestMatcher);
////        this.requiresAuthenticationRequestMatcher = requiresAuthenticationRequestMatcher;
//        this.extractor = extractor;
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
//            throws AuthenticationException, IOException, ServletException {
//        String tokenPayLoad = request.getHeader("Authorization");
//        JwtPreProcessingToken jwtToken = new JwtPreProcessingToken(extractor.extract(tokenPayLoad, request));
//
//        return getAuthenticationManager().authenticate(jwtToken);
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
//                                            Authentication authResult) throws IOException, ServletException {
//        SecurityContext context = SecurityContextHolder.createEmptyContext();
//        context.setAuthentication(authResult);
//        SecurityContextHolder.setContext(context);
//
//        chain.doFilter(
//                request,
//                response
//        );
//    }
//
//    @Override
//    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
//                                              AuthenticationException failed) throws IOException, ServletException {
//        super.unsuccessfulAuthentication(request, response, failed);
//    }
//}
