package com.es.cxp.domainservices.cxp_catalog_domain;

import com.es.cxp.domainservices.catalog.CxpCatalogDomainApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestCxpCatalogDomainApplication {

    public static void main(String[] args) {
        SpringApplication.from(CxpCatalogDomainApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }
}
