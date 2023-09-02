package com.spring.learnreactivespring.service;

import com.spring.learnreactivespring.model.entity.User;
import com.spring.learnreactivespring.model.request.CreateUserRequest;
import com.spring.learnreactivespring.model.vo.UserVO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private List<User> userList=new ArrayList<User>();
    @Override
    public Mono<User> findByUsername(String userName) {
        Optional<User> dbUser= userList.stream().filter(user -> user.getUsername().equals(userName)).findFirst();
        if(dbUser.isPresent()){
            return Mono.just(dbUser.get());
        }else {
          return   Mono.empty();
        }
    }

    @Override
    public Mono<User> save(CreateUserRequest createUserRequest) {
       User user=User.builder().username(createUserRequest.getUserName()).password(createUserRequest.getPassword()).build();
        userList.add(user);
        return Mono.just(user);
    }
}
