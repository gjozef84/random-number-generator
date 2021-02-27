package com.gjozef84.randomnumbergenerator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
@Slf4j
public class RandomNumberGeneratorApplication {

    public static void main(String[] args) {
        Environment env = SpringApplication.run(RandomNumberGeneratorApplication.class, args).getEnvironment();

        String contextPath = env.getProperty("server.servlet.context-path");
        String appName = env.getProperty("spring.application.name");

        log.info("\n----------------------------------------------------------\n\t"
                + "Application '{}' is running! Access URLs:\n\t"
                + "Local: \t\thttp://localhost:{}{}\n\t"
                + "\n----------------------------------------------------------\n\t",
            appName,
            env.getProperty("server.port"),
            contextPath);
    }
}
