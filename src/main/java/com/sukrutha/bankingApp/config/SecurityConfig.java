package com.sukrutha.bankingApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable() // Disable CSRF for testing or with public endpoints like registration
								// (Optional)
				.authorizeHttpRequests()
				//.requestMatchers("/api/v1/customer/register", "/api/v1/customer/login", "/api/v1/accounts/{customerId}")
				.requestMatchers("/api/v1/**")
				.permitAll() // Allow unauthenticated access to register
				.anyRequest().authenticated(); // Protect other endpoints

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
