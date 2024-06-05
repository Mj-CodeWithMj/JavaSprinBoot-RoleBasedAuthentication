package com.example.projectone.projectone.confrigution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.projectone.projectone.models.User;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class MyConfig {

	@Autowired
	private UserDetaileServiceImpls userDetaileServiceImpls;
	
	@Autowired
	private CustomeSuccessHandler customeSuccessHandler;


	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetaileServiceImpls);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors(cors->cors.disable());
		httpSecurity.authorizeHttpRequests(authorize ->{
		authorize.requestMatchers("/user/**").authenticated();
		authorize.anyRequest().permitAll();
		}).csrf(csrf->csrf.disable());
//		httpSecurity.httpBasic(Customizer.withDefaults());
		httpSecurity.formLogin(formlogin -> formlogin.loginPage("/login")
		.loginProcessingUrl("/dologin")
//     	.defaultSuccessUrl("/user/admin")
		.successHandler(customeSuccessHandler).permitAll()
//		.successForwardUrl("/user/admin")
		);
		return httpSecurity.build();
	}
	
	
}
