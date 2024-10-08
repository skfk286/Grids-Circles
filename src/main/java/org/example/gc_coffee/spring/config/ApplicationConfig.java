package org.example.gc_coffee.spring.config;

import jakarta.annotation.PostConstruct;
import org.example.gc_coffee.common.util.LoggerUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ApplicationConfig {
    private static final Logger logger = LoggerUtil.getLogger();

    @Autowired
    private Environment env;

    @PostConstruct
    public void logDatabaseConfig() {
        String dbUrl = env.getProperty("spring.datasource.url");
        String dbUsername = env.getProperty("spring.datasource.username");

        logger.info("Database URL: {}", dbUrl);
        logger.info("Database Username: {}", dbUsername);
    }
}
