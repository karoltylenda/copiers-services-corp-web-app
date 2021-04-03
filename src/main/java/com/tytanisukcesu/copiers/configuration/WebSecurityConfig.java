package com.tytanisukcesu.copiers.configuration;

import com.tytanisukcesu.copiers.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/api/**").authenticated()
                .antMatchers(
                        "/home",
                        "/customers",
                        "/customers/**",
                        "/devices",
                        "/devices/**",
                        "/users",
                        "/users/**",
                        "/models/",
                        "/models/**",
                        "/serviceOrders",
                        "/serviceOrders/**",
                        "/contracts",
                        "/contracts/**",
                        "/articles",
                        "/articles/**",
                        "/settings")
                        .hasAnyRole("ADMIN", "MODERATOR")
                .antMatchers("/templates/pages/fragments/**").authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .failureUrl("/login.html")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/home")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login.html")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .exceptionHandling().accessDeniedPage("/pages/404.html");
    }


    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
