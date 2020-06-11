package com.project.refreshments.config;

import com.project.refreshments.security.JwtSecurityConfigurer;
import com.project.refreshments.security.JwtProvider;
import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity.httpBasic().disable().csrf().disable().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests().antMatchers("/api-jva-refreshments-card/").permitAll()
                .antMatchers("/registration*").permitAll()
                .antMatchers("/login*", "/login/**", "/login").permitAll()
                .antMatchers("/userCheck*").permitAll()
                .antMatchers("/h2/**").permitAll()
                .antMatchers("/**").hasRole("USER")
                .anyRequest().authenticated()
                .and().apply(new JwtSecurityConfigurer(jwtProvider));

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

    @Bean
    ServletRegistrationBean h2servletRegistration()
    {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings("/h2/*");
        return registrationBean;

    }
}
