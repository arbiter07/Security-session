package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.config.security.CustomAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	
	public SecurityConfig(CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
    }
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests((auth) -> auth.requestMatchers("/", "/login", "/loginProc", "/join", "/joinProc").permitAll()
												.requestMatchers("/admin/**").hasRole("ADMIN")
												.requestMatchers("/user/**").hasRole("USER")
												.anyRequest().authenticated());

		http.formLogin((auth) -> auth.loginPage("/login")
									 .loginProcessingUrl("/loginProc")
									 // .defaultSuccessUrl("/", true)
									 .successHandler(customAuthenticationSuccessHandler)
									 .permitAll());
		
		 http.logout((auth) -> auth.logoutUrl("/logout").logoutSuccessUrl("/"));

//		 http.csrf((auth) -> auth.disable());
//		 http.httpBasic(Customizer.withDefaults());

		return http.build();
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder();
	}

}
