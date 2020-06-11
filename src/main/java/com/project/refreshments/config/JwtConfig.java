package com.project.refreshments.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtConfig
{
    private String secretKey = "secret";

    private long validityInMs = 1200000;
}
