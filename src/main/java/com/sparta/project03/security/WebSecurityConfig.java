package com.sparta.project03.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();

        http.authorizeRequests()
                // image 폴더를 login 없이 허용
                .antMatchers("/images/**").permitAll()
                // css 폴더를 login 없이 허용
                .antMatchers("/css/**").permitAll()
                // user/** 을 login 없이 허용
                .antMatchers("/user/**").permitAll()
                // h2-console 을 login 없이 허용
                .antMatchers("/h2-console/**").permitAll()
                // 비회원 게시물 조회 허용
                .antMatchers("/").permitAll()
                .antMatchers("/api/articles").permitAll()
                // 비회원 상세보기 허용
                .antMatchers("/api/comments/**").permitAll()
                .antMatchers("/detail").permitAll()
                .antMatchers("/api/detail/**").permitAll()
                .antMatchers("/api/comment/**").permitAll()
                // 그 외 모든 요청은 인증과정 필요
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/user/login")
                .failureUrl("/user/login/error")
                .loginProcessingUrl("/user/login")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/user/logout").logoutSuccessUrl("/")
                .permitAll();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}