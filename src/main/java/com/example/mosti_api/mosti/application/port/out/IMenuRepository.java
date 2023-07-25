package com.example.mosti_api.mosti.application.port.out;

import com.example.mosti_api.mosti.application.domain.ApiRoleMapping;
import com.example.mosti_api.mosti.application.domain.MenuRoleMapping;

import java.util.List;

public interface IMenuRepository {
    public List<MenuRoleMapping> 사용자별메뉴조회(int roleId);

    List<ApiRoleMapping> 사용자별api리스트조회(int roleId);
}
