package com.spring.learnreactivespring.model.request;

import lombok.*;

@Value
@Builder
@NoArgsConstructor(force = true,access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class LoginRequest {
    private String userName;
    private String password;
}
