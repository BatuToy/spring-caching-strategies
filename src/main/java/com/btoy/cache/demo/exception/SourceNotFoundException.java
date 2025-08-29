package com.btoy.cache.demo.exception;

/*
 * @created 29/08/2025 ~~ 13:01
 * author: batu
 */
public class SourceNotFoundException extends RuntimeException{
    public SourceNotFoundException() {
    }

    public SourceNotFoundException(String message) {
        super(message);
    }
}
