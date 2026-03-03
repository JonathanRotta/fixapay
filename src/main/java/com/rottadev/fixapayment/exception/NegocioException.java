package com.rottadev.fixapayment.exception;

public class NegocioException extends RuntimeException{

    public NegocioException(String mensagem){
        super(mensagem);
    }
}
