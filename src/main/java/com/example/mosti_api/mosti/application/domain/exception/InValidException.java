package com.example.mosti_api.mosti.application.domain.exception;

public class InValidException extends RuntimeException {

    private static final long serialVersionUID = 6410744374489766116L;

    public InValidException(String msg){
        super(msg);
    }
}
