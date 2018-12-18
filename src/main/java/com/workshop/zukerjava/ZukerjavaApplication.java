package com.workshop.zukerjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ZukerjavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZukerjavaApplication.class, args);
    }
}
