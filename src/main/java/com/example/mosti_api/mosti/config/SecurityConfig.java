package com.example.mosti_api.mosti.config;

import com.example.mosti_api.mosti.application.security.CustomJwtAuthenticationConverter;
import com.example.mosti_api.mosti.application.security.Oauth2AuthenticationFailureHandler;
import com.example.mosti_api.mosti.application.security.Oauth2AuthenticationSuccessHandler;
import com.example.mosti_api.mosti.application.security.Oauth2LogoutSuccessHandler;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {
    private static final Logger log =  LoggerFactory.getLogger(SecurityConfig.class);
    private static final String DEFAULT_PAGE= "/page/user";
    private static final String LOGIN_PAGE= "/page/login";

    private final UserDetailsService userDetailsService;
    private final  CustomJwtAuthenticationConverter customJwtAuthenticationConverter;

    public SecurityConfig(UserDetailsService userDetailsService,
        CustomJwtAuthenticationConverter customJwtAuthenticationConverter) {
        this.userDetailsService = userDetailsService;
        this.customJwtAuthenticationConverter = customJwtAuthenticationConverter;
    }
/*    @Bean
    public AuthenticationProvider authenticationProvider(){
        return new EmbAuthenticationProvider(passwordEncoder(), userDetailsService);
    }*/

    @Bean
    public AuthenticationFailureHandler oauth2AuthenticationFailureHandler(){
        return new Oauth2AuthenticationFailureHandler();
    }

    @Bean
    public AuthenticationSuccessHandler oauth2AuthenticationSuccessHandler(){
        return new Oauth2AuthenticationSuccessHandler();
    }

    @Bean
    public LogoutSuccessHandler oauthLogoutHandler(){
        return new Oauth2LogoutSuccessHandler();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        //간단하게 사용자 조회하고, 암호만 체크하는 경우 사용
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }


    @Bean
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authz -> authz
            .requestMatchers("/api/**").authenticated()
            .anyRequest().permitAll()
        );

        http.oauth2Login();
        http.oauth2ResourceServer()
            .jwt()
            .jwtAuthenticationConverter(customJwtAuthenticationConverter);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors()
            .and().csrf().disable();
        return http.build();

    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList(HttpMethod.POST.name(), HttpMethod.GET.name(),
            HttpMethod.PUT.name(), HttpMethod.DELETE.name(),
            HttpMethod.OPTIONS.name()));
        configuration.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.debug(false)
//                .ignoring()
//                .antMatchers("/assets/**", "/favicon.ico");
//    }

    @Bean
    Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl("http://172.30.1.157:8443")
                .realm("master")
                .clientId("admin-cli")
                .grantType(OAuth2Constants.PASSWORD)
                .username("mostidevadmin")
                .password("1")
                .build();
    }




}

