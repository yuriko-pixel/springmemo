package com.react.springmemo.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	 //参考:https://stackoverflow.com/questions/61441508/spring-security-with-rest-api-with-react
	 @Bean
	 CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();

	        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
	        configuration.setAllowedHeaders(List.of("*"));
	        configuration.setAllowedMethods(Arrays.asList("GET","POST", "OPTIONS"));
	        configuration.setAllowCredentials(true);

	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);

	        return source;
	    }



	 @Autowired
	    private DataSource dataSource;

	    private static final String USER_SQL = "SELECT"
	            + "    user_id,"
	            + "    password,"
	            + "    enabled"
	            + " FROM"
	            + "    m_user"
	            + " WHERE"
	            + "    user_id = ?";


	    private static final String ROLE_SQL = "SELECT"
	            + "    m_user.user_id,"
	            + "    role.role_name"
	            + " FROM"
	            + "    m_user INNER JOIN t_user_role AS user_role"
	            + " ON"
	            + "    m_user.user_id = user_role.user_id"
	            + "    INNER JOIN m_role AS role"
	            + " ON"
	            + "    user_role.role_id = role.role_id"
	            + " WHERE"
	            + "    m_user.user_id = ?";

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {

			http.cors().and()
             .csrf().disable()
             .authorizeRequests()
             .antMatchers(HttpMethod.OPTIONS).permitAll()
             .antMatchers(HttpMethod.GET, "/api/*").authenticated()
             .anyRequest().authenticated()
             .and()
             .httpBasic();
	    }

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	        auth.jdbcAuthentication()
	            .dataSource(dataSource)
	            .usersByUsernameQuery(USER_SQL)
	            .authoritiesByUsernameQuery(ROLE_SQL)
	            .passwordEncoder(passwordEncoder());

	    }
}
