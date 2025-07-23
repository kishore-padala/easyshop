package com.es.cxp.domainservices.notification;

import org.springframework.boot.SpringApplication;

public class TestCxpNotificationDomainApplication {

    public static void main(String[] args) {
        SpringApplication.from(CxpNotificationDomainApplication::main)
                .with(ContainersConfig.class)
                .run(args);
    }
}
