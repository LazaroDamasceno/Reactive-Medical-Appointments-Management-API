package com.api.v1.patient;

import com.api.v1.user.annotations.SSN;

public class PatientNotFoundException extends RuntimeException {

    public PatientNotFoundException(@SSN String ssn) {
        super("Patient whose SSN is %s was not found.".formatted(ssn));
    }

}
