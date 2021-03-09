package com.gjozef84.randomnumbergenerator.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(GeneratorProperties.class)
public class ContextConfig {

    @Bean
    public static GeneratorProperties generatorProperties() {
        return new GeneratorProperties();
    }
}
