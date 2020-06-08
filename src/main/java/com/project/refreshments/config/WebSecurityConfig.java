package com.project.refreshments.config;

import java.util.HashMap;
import java.util.Map;

import com.project.refreshments.security.JwtSecurityConfigurer;
import com.project.refreshments.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity.httpBasic().disable().csrf().disable().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests().antMatchers("/api-jva-refreshments-card/").permitAll()
                .antMatchers("/user/registration*").permitAll()
                .antMatchers("/h2/**").permitAll()
                .antMatchers("/**").hasRole("USER")
                .antMatchers("/user/login/**", "/login").permitAll()
                .antMatchers("/", "/css/**", "/*.css").permitAll()
                .antMatchers("/user/homepage").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/user/login")
                .loginProcessingUrl("/user/login").defaultSuccessUrl("/homepage", true).failureUrl("/login?error").permitAll()
                .and().apply(new JwtSecurityConfigurer(jwtTokenProvider));

                httpSecurity.headers().frameOptions().disable();
    }

    @Bean
    public PasswordEncoder delegatingPasswordEncoder()
    {
        PasswordEncoder defaultEncoder = new StandardPasswordEncoder();
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());

        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("bcrypt", encoders);

        passwordEncoder.setDefaultPasswordEncoderForMatches(defaultEncoder);

        return passwordEncoder;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }

}
