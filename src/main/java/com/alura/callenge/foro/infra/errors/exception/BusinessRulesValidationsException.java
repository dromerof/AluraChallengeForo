package com.alura.callenge.foro.infra.errors.exception;

public class BusinessRulesValidationsException extends RuntimeException{

    public BusinessRulesValidationsException(String message){
        super(message);
    }
}
