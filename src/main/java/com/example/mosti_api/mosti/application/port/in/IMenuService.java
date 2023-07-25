package com.example.mosti_api.mosti.application.port.in;

import com.example.mosti_api.mosti.application.domain.ApiRoleMapping;
import com.example.mosti_api.mosti.application.domain.MenuRoleMapping;

import java.util.List;

public interface IMenuService {
    public List<MenuRoleMapping> 사용자별메뉴조회(int RoleId);

    List<ApiRoleMapping> 사용자별api리스트조회(int roleId);
}
