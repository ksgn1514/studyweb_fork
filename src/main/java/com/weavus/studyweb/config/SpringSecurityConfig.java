package com.weavus.studyweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public BCryptPasswordEncoder encoderPw() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authorizeHttpRequests) ->
                                authorizeHttpRequests
                                        .requestMatchers("/study/**").authenticated()
                                        .requestMatchers("/community/**").authenticated()
                                        .requestMatchers("/user/**").authenticated()
                                        .anyRequest().permitAll()
//                                .antMatchers("/manager/**").hasRole("MANAGER")

                )
                .formLogin((formLogin) ->
                        formLogin.loginPage("/loginForm")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/")
                                .failureUrl("/login/fail")
                )
                .logout((logout) -> logout.logoutSuccessUrl("/"))
        ;
        return http.build();
    }
}
