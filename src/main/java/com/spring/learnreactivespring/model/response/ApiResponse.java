package com.spring.learnreactivespring.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private int code;
    private String errorDesc;
}
