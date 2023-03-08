package com.office.calender.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain fileterChain(HttpSecurity http) throws Exception {
		
		http.cors().disable()				// CORS 방지
			.csrf().disable()				// CSRF 방지
			.formLogin().disable()			// 기본 로그인 페이지 사용 안함.
			.headers().frameOptions().disable();
		
		return http.build();
	}
	
}
