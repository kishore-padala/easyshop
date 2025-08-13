package com.es.easyshop.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class EasyshopWebappApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyshopWebappApplication.class, args);
    }
}
