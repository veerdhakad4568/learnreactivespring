package com.spring.learnreactivespring.routers;

import com.spring.learnreactivespring.handlers.AuthHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class AuthRouter {
    @Bean
    public RouterFunction authRoute(AuthHandler authHandler) {
        return RouterFunctions
                .route(POST("/auth/login").and(accept(MediaType.APPLICATION_JSON)), authHandler::login)
                .andRoute(POST("/auth/signup").and(accept(MediaType.APPLICATION_JSON)), authHandler::signUp);
    }

}
