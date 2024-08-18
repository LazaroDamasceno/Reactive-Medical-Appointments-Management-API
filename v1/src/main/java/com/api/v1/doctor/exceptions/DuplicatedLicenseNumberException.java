package com.api.v1.doctor.exceptions;

public class DuplicatedLicenseNumberException extends RuntimeException {

    public DuplicatedLicenseNumberException() {
        super("The input license number is already registered.");
    }

}
