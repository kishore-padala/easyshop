package com.es.cxp.domainservices.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
// @EnableConfigurationProperties(ApplicationProperties.class)
@ConfigurationPropertiesScan
public class CxpOrdermanagementDomainApplication {

    public static void main(String[] args) {
        SpringApplication.run(CxpOrdermanagementDomainApplication.class, args);
    }
}
