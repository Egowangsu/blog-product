package com.wyx.blog.exception;

public class NotFoundException extends MyException {
    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }
}
