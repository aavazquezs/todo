package com.aavs.postgrado.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.aavs.postgrado.repositories.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private UserRepository repo;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/","/home", "/about", 
					"/js/**", "/images/**", "/css/**")
				.permitAll()
			.anyRequest().authenticated()
			.and()
		//form login config
		.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/perform_login")
			.defaultSuccessUrl("/",true)
		    .failureUrl("/login?error=true")
			.permitAll()
			.and()
		.logout()
			.logoutSuccessUrl("/login")
			.logoutUrl("/perform_logout")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.permitAll()
			.and()
		.csrf().disable()
		;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.inMemoryAuthentication()
        	.withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
        .and()
        	.withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
        .and()
        	.withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
	}
	
	@Bean 
	public PasswordEncoder passwordEncoder() { 
	    return new BCryptPasswordEncoder(); 
	}
}
