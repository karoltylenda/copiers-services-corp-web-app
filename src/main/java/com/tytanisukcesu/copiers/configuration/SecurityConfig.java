package com.tytanisukcesu.copiers.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final ObjectMapper objectMapper;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.jdbcAuthentication()
                    .dataSource(dataSource);
//                    .withUser("test")
//                    .password("{bcrypt}" + new BCryptPasswordEncoder().encode("test"))
//                    .roles("USER");
        }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); //wylaczenie csrfa
        http
                .authorizeRequests()
                .antMatchers("/swagger-ui.html").permitAll() //swagger aby byl publicznie
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
    }

    public JsonObjectAuthenticationFilter authenticationFilter() throws Exception{
        JsonObjectAuthenticationFilter authenticationFilter = new JsonObjectAuthenticationFilter(objectMapper);
        authenticationFilter.setAuthenticationSuccessHandler(successHandler);
        authenticationFilter.setAuthenticationFailureHandler(failureHandler);
        authenticationFilter.setAuthenticationManager(super.authenticationManager()); //ustawiamy domyslny manager
        return authenticationFilter;
    }



}





