package com.example.mosti_api.mosti.application.dto.request.user;

import com.example.mosti_api.framework.validate.EnumVal;
import com.example.mosti_api.mosti.application.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UserCreateReqDto(
        String userName,
        String password,
        String email,
        String lastName,
        String firstName
) {}
