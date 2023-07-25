package com.example.mosti_api.mosti.config;

import com.example.mosti_api.mosti.application.security.ApiRoleCheckInterceptor;
import com.example.mosti_api.mosti.application.security.MenuRoleCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig  implements WebMvcConfigurer {
    private final MenuRoleCheckInterceptor menuRoleCheckInterceptor;
    private final ApiRoleCheckInterceptor apiRoleCheckInterceptor;

    public WebMvcConfig(MenuRoleCheckInterceptor menuRoleCheckInterceptor, ApiRoleCheckInterceptor apiRoleCheckInterceptor) {
        this.menuRoleCheckInterceptor = menuRoleCheckInterceptor;
        this.apiRoleCheckInterceptor = apiRoleCheckInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*registry.addInterceptor(apiRoleCheckInterceptor)
                .addPathPatterns("/api/**");*/

        /*registry.addInterceptor(menuRoleCheckInterceptor)
                .addPathPatterns("/page/**")
                .excludePathPatterns("/page/login","/page/logout");*/
    }
}
