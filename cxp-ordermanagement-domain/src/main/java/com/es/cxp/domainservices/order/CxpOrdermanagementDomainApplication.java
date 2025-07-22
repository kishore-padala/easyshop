package com.es.cxp.domainservices.order;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
// @EnableConfigurationProperties(ApplicationProperties.class)
@ConfigurationPropertiesScan
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "10m") // How long your job duration takes.
public class CxpOrdermanagementDomainApplication {

    public static void main(String[] args) {
        SpringApplication.run(CxpOrdermanagementDomainApplication.class, args);
    }
}
