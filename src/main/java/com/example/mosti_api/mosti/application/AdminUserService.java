package com.example.mosti_api.mosti.application;

import com.example.mosti_api.framework.exception.ApplicationException;
import com.example.mosti_api.framework.helper.Do;
import com.example.mosti_api.mosti.application.domain.AdminUser;
import com.example.mosti_api.mosti.application.dto.AdminUserDto;
import com.example.mosti_api.mosti.application.dto.AdminUserDtoFactory;
import com.example.mosti_api.mosti.application.port.in.IAdminUserService;
import com.example.mosti_api.mosti.application.port.out.IAdminUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminUserService implements IAdminUserService {
    private final IAdminUserRepository adminUserRepository;

    public AdminUserService(IAdminUserRepository adminUserRepository) {
        this.adminUserRepository = adminUserRepository;
    }

    @Override
    public List<AdminUserDto> 목록조회() {
        List<AdminUser> users = this.adminUserRepository.목록조회();
        if (users == null) return null;

        return users.stream()
                .map(AdminUserDtoFactory::createBy)
                .collect(Collectors.toList());
    }

    @Override
    public AdminUserDto 조회(String id) {
        AdminUser user = this.adminUserRepository.조회(id);
        return AdminUserDtoFactory.createBy(user);
    }

    @Override
    public void 추가(String 이름, String 암호, String 이메일) {
        AdminUser user = this.adminUserRepository.조회(이름);
        if (Do.있음(user))
            throw new ApplicationException("같은 이름의 사용자가 있어서 생성할 수 없습니다.");

        this.adminUserRepository.추가(AdminUser.생성(이름, 암호, 이메일));
    }

    @Override
    public void 수정(String id, String 이름, String 암호, String 이메일, String 상태) {
        AdminUser user = this.adminUserRepository.조회(이름);
        if (Do.있음(user))
            throw new ApplicationException("같은 이름의 사용자가 있어서 수정할 수 없습니다.");

        user = new AdminUser(id);
        user.setName(이름);
        user.setStatus(상태);
        this.adminUserRepository.수정(user);

        if (Do.있음(암호))
            this.adminUserRepository.암호수정(id, 암호);
    }

    @Override
    public void 삭제(String id) {
        this.adminUserRepository.삭제(id);
    }
}
