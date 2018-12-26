package com.workshop.zukerjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ServletComponentScan(basePackages = {"com.workshop.zukerjava.security"})
public class ZukerjavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZukerjavaApplication.class, args);
    }
}
