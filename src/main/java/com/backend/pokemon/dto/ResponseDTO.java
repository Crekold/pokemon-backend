package com.backend.pokemon.dto;

public class ResponseDTO {
    private String code;
    private Object result;
    private String message;

    public ResponseDTO() {
    }

    public ResponseDTO(String code, Object result,String message) {
        this.code = code;
        this.result = result;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public Object getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "{" +
                "\"code\":\"" + code + '\"' +
                ", \"result\":" + result +
                ", \"message\":\"" + message + '\"' +
                '}';
    }

}