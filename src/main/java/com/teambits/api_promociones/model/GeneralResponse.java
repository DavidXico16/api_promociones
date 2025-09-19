package com.teambits.api_promociones.model;

import lombok.Getter;
import lombok.Setter;

public class GeneralResponse {
    
        @Setter
    @Getter
    private int code;

    @Setter
    @Getter
    private String message;

    @Setter
    @Getter
    private Object result;

    public GeneralResponse(int code, String  message, Object result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }
}
