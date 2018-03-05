package com.uterm.errors;


/**
 * BadReqeustException
 */
@SuppressWarnings("serial")
public class BadReqeustException extends RuntimeException { 
    public BadReqeustException(String message) {
        super(message);
    }

    public BadReqeustException() {
        super();
    }
}