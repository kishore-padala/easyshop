package com.es.cxp.domainservices.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
// @EnableConfigurationProperties(ApplicationProperties.class) // it scans the application.properties file which has the
// prefix.
@ConfigurationPropertiesScan // it scans the root package identify all the classes.
public class CxpCatalogDomainApplication {

    public static void main(String[] args) {
        SpringApplication.run(CxpCatalogDomainApplication.class, args);
    }
}
