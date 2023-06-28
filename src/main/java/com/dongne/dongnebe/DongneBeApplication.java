package com.dongne.dongnebe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DongneBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DongneBeApplication.class, args);
    }

}
