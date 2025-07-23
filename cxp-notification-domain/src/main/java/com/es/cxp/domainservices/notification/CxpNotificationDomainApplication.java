package com.es.cxp.domainservices.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CxpNotificationDomainApplication {

    public static void main(String[] args) {
        SpringApplication.run(CxpNotificationDomainApplication.class, args);
    }
}
