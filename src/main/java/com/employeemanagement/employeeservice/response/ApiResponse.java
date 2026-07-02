package com.employeemanagement.employeeservice.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApiResponse<T> {

    private boolean success;

    private int status;

    private String message;

    private LocalDateTime timestamp;

    private T data;
}