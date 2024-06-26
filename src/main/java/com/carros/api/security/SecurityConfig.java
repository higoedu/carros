package com.carros.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.carros.api.security.jwt.JwtAuthenticationFilter;
import com.carros.api.security.jwt.JwtAuthorizationFilter;
import com.carros.api.security.jwt.handler.AccessDeniedHandler;
import com.carros.api.security.jwt.handler.UnauthorizedHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;
	
	@Autowired
    private UnauthorizedHandler unauthorizedHandler;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		http
			.authorizeRequests()
			.anyRequest().authenticated()
			.and().httpBasic()
			.and().csrf().disable();
		*/
		
		AuthenticationManager authManager = authenticationManager();

        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .antMatchers(HttpMethod.GET, "/api/v1/login").permitAll()
                .and().csrf().disable()
                .addFilter(new JwtAuthenticationFilter(authManager))
                .addFilter(new JwtAuthorizationFilter(authManager, userDetailsService))
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//super.configure(auth);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		/*
		auth
			.inMemoryAuthentication().passwordEncoder(encoder)
		*/
			
				/*
				.withUser("user").password("password").roles("USER")
				.and()
				.withUser("admin").password("password").roles("USER", "ADMIN");
				*/
			
				/*
				.withUser("user").password(encoder.encode("user")).roles("USER")
				.and()
				.withUser("admin").password(encoder.encode("admin")).roles("USER", "ADMIN");
				*/
		
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
	}
}
