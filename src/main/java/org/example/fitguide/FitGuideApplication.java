package org.example.fitguide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@EnableJpaAuditing
public class FitGuideApplication {

    public static void main(String[] args) {
        SpringApplication.run(FitGuideApplication.class, args);
    }

}
