package com.spring.learnreactivespring.model.vo;

import lombok.*;

@Value
@Builder
@NoArgsConstructor(force = true,access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserVO {
    private String userName;
    private String password;
}
