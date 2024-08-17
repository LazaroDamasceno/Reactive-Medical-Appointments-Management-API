package com.api.v1.user.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("User was not found.");
    }

}
