package com.trzaskom.security;

import com.trzaskom.security.jwt.JWTConfigurer;
import com.trzaskom.security.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by miki on 2019-01-04.
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;

    public SecurityConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager customauthenticationManagerBean() throws Exception {
        return authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
                    http
                        .csrf()
                            .disable()
                        .cors()
                            .and()
                        .sessionManagement()
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                            .and()
                //.httpBasic() // optional, if you want to access
                //  .and()     // the services from a browser
                        .authorizeRequests()
                            .antMatchers("/signup").permitAll()
                            .antMatchers("/login").permitAll()
                            .antMatchers("/public").permitAll()
                            .antMatchers("/socket/**").permitAll()
                            .anyRequest().authenticated()
                            .and()
                        .apply(new JWTConfigurer(this.tokenProvider));
                    // @formatter:on
    }

}

