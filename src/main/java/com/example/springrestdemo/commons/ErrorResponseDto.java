package com.example.springrestdemo.commons;

import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class ErrorResponseDto {
    @NonNull
    private String message;
}
