package ca.paruvendu.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import ca.paruvendu.service.UserSecurityService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment env;

	@Autowired
	private UserSecurityService userSecurityService;

	private BCryptPasswordEncoder passwordEncoder(){
		return SecurityUtility.passwordEncoder();
		
		}
	
	

	private static final String[] PUBLIC_MATCHERS = {

			"/css/**", "/js/**", "/image/**", "/paruvendu/**", "/user/**", "/carad/**","/appad/**" };

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable().httpBasic().and().authorizeRequests()
		.antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public HttpSessionStrategy httpSessionStrategy(){
		
		return new HeaderHttpSessionStrategy();
		
	}
	
	@Bean
	  CorsConfigurationSource corsConfigurationSource() {
	      CorsConfiguration configuration = new CorsConfiguration();
	      configuration.setAllowedOrigins(Arrays.asList("https://localhost:2400"));
	      configuration.setAllowedMethods(Arrays.asList("GET","POST"));
	      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	      source.registerCorsConfiguration("/**", configuration);
	      return source;
	  }
}
