package com.wyx.blog.exception;

public class TagNameEmptyException extends MyException {
    public TagNameEmptyException() {
        super();
    }

    public TagNameEmptyException(String message) {
        super(message);
    }
}
