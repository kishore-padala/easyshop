package com.es.cxp.domainservices.catalog;

import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCxpCatalogDomainApplication {

    public static void main(String[] args) {
        SpringApplication.from(CxpCatalogDomainApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }
}
