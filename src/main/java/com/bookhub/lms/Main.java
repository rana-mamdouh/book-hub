package com.bookhub.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication
@Validated

public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
