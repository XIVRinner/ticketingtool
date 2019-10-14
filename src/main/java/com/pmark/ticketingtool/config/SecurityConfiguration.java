package com.pmark.ticketingtool.config;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);

	@Value("${ticketingtool.frontend:http://localhost:8080")
	private String url;

	@Inject
	HashPasswordAuthenticationProvider hashProvider;


	@PostConstruct
	private void init() {
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(hashProvider);

	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
				.authorizeRequests().antMatchers("/authorize").permitAll()
				.and()
				.authorizeRequests().antMatchers("/private").authenticated()
				.and()
				.authorizeRequests().antMatchers("/public").permitAll()
				.and()
				.addFilter(new JWTAuthorizationFilter(authenticationManager()))
				.addFilter(new JWTAuthenticationFilter(authenticationManager()))
				.sessionManagement().sessionCreationPolicy(STATELESS);
		//http.httpBasic().and().authorizeRequests().anyRequest().permitAll().and().csrf().disable();



	}

	@Bean
	public FilterRegistrationBean simpleCorsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		// *** URL below needs to match the Vue client URL and port ***
		config.setAllowedOrigins(Collections.singletonList(url));
		config.setAllowedMethods(Collections.singletonList("*"));
		config.setAllowedHeaders(Collections.singletonList("*"));
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

}
