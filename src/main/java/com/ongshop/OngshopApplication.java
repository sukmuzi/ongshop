package com.ongshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OngshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(OngshopApplication.class, args);
    }

}
