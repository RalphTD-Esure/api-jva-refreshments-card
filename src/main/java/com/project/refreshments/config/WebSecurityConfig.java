package com.project.refreshments.config;

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
                .loginProcessingUrl("/user/postLogin").defaultSuccessUrl("/homepage.html").failureUrl("/login?error").permitAll()
                .and().apply(new JwtSecurityConfigurer(jwtTokenProvider));

                httpSecurity.headers().frameOptions().disable();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }

}
