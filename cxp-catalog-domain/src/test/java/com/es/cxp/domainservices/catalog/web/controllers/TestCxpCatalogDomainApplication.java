package com.es.cxp.domainservices.catalog.web.controllers;

import com.es.cxp.domainservices.catalog.CxpCatalogDomainApplication;
import com.es.cxp.domainservices.catalog.TestContainersConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class TestCxpCatalogDomainApplication {

    public static void main(String[] args) {
        SpringApplication.from(CxpCatalogDomainApplication::main)
                .with(TestContainersConfiguration.class)
                .run(args);
    }
}
