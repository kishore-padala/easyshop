package com.es.cxp.domainservices.order;

import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class TestCxpOrdermanagementDomainApplication {

    public static void main(String[] args) {
        SpringApplication.from(CxpOrdermanagementDomainApplication::main)
                .with(ContainersConfig.class)
                .run(args);
    }
}
