package com.api.v1.user.mappers;

import com.api.v1.user.domain.User;
import com.api.v1.user.dtos.UserResponseDto;

public class UserResponseMapper {

    public static UserResponseDto map(User user) {
        return new UserResponseDto(
                user.getFullName(),
                user.getBirthDate(),
                user.getSsn(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getGender()
        );
    }

}
