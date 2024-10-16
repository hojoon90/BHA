package com.bupjangsa.exception;

import lombok.Getter;

@Getter
public class AuthorizeException extends RuntimeException{

    public AuthorizeException(String message) {
        super(message);
    }
}
