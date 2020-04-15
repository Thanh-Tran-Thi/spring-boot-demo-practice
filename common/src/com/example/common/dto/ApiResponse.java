package com.example.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse <T>{
    private String status;
    private String message;
    private T result;
    private Paging paging;

    public ApiResponse() {
    }

    public ApiResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
    public ApiResponse(String status, String message, T result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public ApiResponse(String status, String message, T result, Paging metaData) {
        this.status = status;
        this.message = message;
        this.result = result;
        this.paging = metaData;
    }
}
