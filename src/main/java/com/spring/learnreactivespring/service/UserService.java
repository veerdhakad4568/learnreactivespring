package com.spring.learnreactivespring.service;

import com.spring.learnreactivespring.model.entity.User;
import com.spring.learnreactivespring.model.request.CreateUserRequest;
import com.spring.learnreactivespring.model.vo.UserVO;
import reactor.core.publisher.Mono;

public interface UserService {
    public Mono<User> findByUsername(String userName);
    public Mono<User> save(CreateUserRequest createUserRequest);
}
