package com.sukrutha.bankingApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable() // Disable CSRF for testing or with public endpoints like registration
								// (Optional)
				.authorizeHttpRequests()
				//.requestMatchers("/api/v1/customer/register", "/api/v1/customer/login", "/api/v1/accounts/{customerId}")
				.requestMatchers("/api/v1/customer/**").permitAll() // Allow unauthenticated access to register
				.anyRequest().authenticated()// Protect other endpoints
				.and()
				.formLogin()
				.and()
				.httpBasic();
				

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	 // Define an in-memory user with BCrypt encoded password
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("password")) // Encode the password using BCrypt
                .roles("admin")
                .build();
        
        return new InMemoryUserDetailsManager(admin);
    }
}
