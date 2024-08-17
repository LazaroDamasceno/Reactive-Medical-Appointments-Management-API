package com.api.v1.user.exceptions;

public class DuplicatedSsnException extends RuntimeException {

    public DuplicatedSsnException() {
        super("The input SSN is already in use.");
    }

}
