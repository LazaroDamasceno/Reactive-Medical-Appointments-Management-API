package com.api.v1.medicalappointment.exceptions;

public class ConflictingDateException extends RuntimeException {

    public ConflictingDateException() {
        super("The input booking date must be, at least, equals to the next date.");
    }

}
