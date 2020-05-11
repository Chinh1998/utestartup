package com.quangchinh.demo.config;

import com.quangchinh.demo.security.JwtAuthenticationEntryPoint;
import com.quangchinh.demo.security.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final JwtRequestFilter jwtRequestFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    public SpringSecurityConfig(UserDetailsService userDetailsService, JwtRequestFilter jwtRequestFilter, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    // Secure the endpoints with HTTP Basic authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and().authorizeRequests()
                // User controller
                .antMatchers("/users/register").permitAll()
                .antMatchers("/users/login").permitAll()
                .antMatchers("/users/islogin").permitAll()
                .antMatchers("/users").hasRole("ADMIN")
                .antMatchers("/users/**").hasAnyRole("ADMIN", "MEMBER")
                .antMatchers(HttpMethod.POST, "/users").hasAnyRole("ADMIN", "MEMBER")
                .antMatchers(HttpMethod.DELETE, "/users").hasAnyRole("ADMIN", "MEMBER")
                .antMatchers(HttpMethod.PUT, "/users").hasAnyRole("ADMIN", "MEMBER")
                //News Controller
                .antMatchers("/news").permitAll()
                .antMatchers("/news/mypost").permitAll()
                .antMatchers(HttpMethod.GET,"/news/search").permitAll()
                .antMatchers(HttpMethod.POST, "/news/**").hasAnyRole("ADMIN", "MEMBER")
              .antMatchers(HttpMethod.DELETE, "/news").hasAnyRole("ADMIN", "MEMBER")

                .antMatchers(HttpMethod.PUT, "/news").hasAnyRole("ADMIN", "MEMBER")

                // Disable form login
                .and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable()
                .formLogin()
                .and()
                .logout();
        // Add a filter to validate the tokens with every request
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }
}
