package com.spring.learnreactivespring.handlers;

import com.spring.learnreactivespring.model.request.CreateUserRequest;
import com.spring.learnreactivespring.model.request.LoginRequest;
import com.spring.learnreactivespring.model.response.ApiResponse;
import com.spring.learnreactivespring.model.response.LoginResponse;
import com.spring.learnreactivespring.service.UserService;
import com.spring.learnreactivespring.security.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class AuthHandler {
    private final UserService userService;
    private final JWTUtil tokenProvider;
    private BCryptPasswordEncoder passwordEncoder;
    public Mono<ServerResponse> login(ServerRequest request) {
        return request.bodyToMono(LoginRequest.class).flatMap(login -> userService.findByUsername(login.getUserName())
                .flatMap(user -> {
                    if (passwordEncoder.matches(login.getPassword(), user.getPassword())) {
                        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(new LoginResponse(tokenProvider.generateToken(user))));
                    } else {
                        return ServerResponse.badRequest().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(ApiResponse.builder().code(400).errorDesc("Invalid credentials").build()));
                    }
                }).switchIfEmpty(ServerResponse.badRequest().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(ApiResponse.builder().code(400).errorDesc("User does not exist").build()))));
    }

    public Mono<ServerResponse> signUp(ServerRequest request) {
        return request.bodyToMono(CreateUserRequest.class).map(userRequest -> {
           return CreateUserRequest.builder().password(passwordEncoder.encode(userRequest.getPassword())).userName(userRequest.getUserName()).build();
        }).flatMap(user -> userService.findByUsername(user.getUserName())
                .flatMap(dbUser -> ServerResponse.badRequest().body(BodyInserters.fromValue(ApiResponse.builder().code(400).errorDesc("User already exist").build())))
                .switchIfEmpty(userService.save(user).flatMap(savedUser -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(savedUser)))));
    }
}
