package com.api.v1.doctor.domain;

import com.api.v1.user.domain.User;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Document(collection = "v1_doctors")
public class Doctor {

    @Id
    private final UUID id;

    @Field
    private final String licenseNumber;

    @Field
    private User user;

    @Field
    private String hiringDate;

    @Field
    private String terminationDate;

    public Doctor(
            UUID id,
            String licenseNumber,
            User user,
            String hiringDate
    ) {
        this.id = id;
        this.licenseNumber = licenseNumber;
        this.user = user;
        this.hiringDate = hiringDate;
    }

    public void update(User user) {
        this.user = user;
    }

    public void terminateDoctor() {
        terminationDate = ZonedDateTime.now().toString();
    }

}
