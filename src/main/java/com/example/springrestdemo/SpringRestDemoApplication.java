package com.example.springrestdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringRestDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestDemoApplication.class, args);
    }

}
