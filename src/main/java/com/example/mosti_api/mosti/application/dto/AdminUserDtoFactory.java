package com.example.mosti_api.mosti.application.dto;

import com.example.mosti_api.mosti.application.domain.AdminUser;

public class AdminUserDtoFactory {
    public static AdminUserDto createBy(AdminUser user) {
        AdminUserDto adminUserDto = new AdminUserDto();
        adminUserDto.setId(user.getId());
        adminUserDto.setName(user.getName());
        adminUserDto.setEmail(user.getEmail());
        adminUserDto.setCreatedOn(user.getCreatedOn().toString());
        adminUserDto.setStatus(user.getStatus());

        return adminUserDto;
    }
}
