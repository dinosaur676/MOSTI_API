package com.example.mosti_api.mosti.application.port.in;

import com.example.mosti_api.mosti.application.dto.AdminUserDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IAdminUserService {

    List<AdminUserDto> 목록조회();
    AdminUserDto 조회(String id);

    @Transactional
    void 추가(String 이름, String 암호, String 이메일);

    @Transactional
    void 수정(String id, String 이름, String 암호, String 이메일, String 상태);

    @Transactional
    void 삭제(String id);
}
