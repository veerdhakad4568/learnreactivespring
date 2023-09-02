package com.spring.learnreactivespring.handlers;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class TaskHandler {
    public Mono<ServerResponse> createTask(ServerRequest serverRequest) {
        return ServerResponse.ok().body(BodyInserters.fromValue("Task has been create!"));
    }

    public Mono<ServerResponse> getAllTask(ServerRequest serverRequest) {
        return ServerResponse.ok().body(BodyInserters.fromValue("All task has been returned!"));
    }

    public Mono<ServerResponse> getTask(ServerRequest serverRequest) {
        return ServerResponse.ok().body(BodyInserters.fromValue("Task!"));
    }

    public Mono<ServerResponse> updateTask(ServerRequest serverRequest) {
        return ServerResponse.ok().body(BodyInserters.fromValue("Task has been updated!"));
    }
}
