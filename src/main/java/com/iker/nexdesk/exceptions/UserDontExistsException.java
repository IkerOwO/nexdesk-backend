package com.iker.nexdesk.exceptions;

public class UserDontExistsException extends RuntimeException {
    public UserDontExistsException(String message) {
        super(message);
    }
}
