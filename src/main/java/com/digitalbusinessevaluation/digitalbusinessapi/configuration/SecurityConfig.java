package com.digitalbusinessevaluation.digitalbusinessapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.session.SessionManagementFilter;

import com.digitalbusinessevaluation.digitalbusinessapi.configuration.pojo.CorsFilter;

@Configuration
@EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
//	private CustomBasicAuthenticationEntryPoint authEntryPoint;
//
//	@Override
//	@Autowired
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("user1").password(new BCryptPasswordEncoder().encode("secret1"))
//				.roles("USER").and().withUser("user2").password(new BCryptPasswordEncoder().encode("secret2"))
//				.roles("USER");
//	}
//	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(corsFilter(), SessionManagementFilter.class);
		http.csrf().disable().httpBasic().and().authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS).permitAll()
				.antMatchers(HttpMethod.GET).permitAll().antMatchers(HttpMethod.POST).permitAll()
				.antMatchers(HttpMethod.PUT).permitAll().antMatchers(HttpMethod.DELETE).permitAll();
		http.headers().frameOptions().disable();
	}

	@Bean
	CorsFilter corsFilter() {
		CorsFilter filter = new CorsFilter();
		return filter;
	}

}