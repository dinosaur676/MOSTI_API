package com.example.mosti_api.mosti.application.dto.request.user;

import com.example.mosti_api.mosti.application.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserUpdateReqDto (

        String userName,
        String email,
        String lastName,
        String firstName
)
{}
