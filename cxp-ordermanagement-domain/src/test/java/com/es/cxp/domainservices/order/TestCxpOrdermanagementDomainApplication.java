package com.es.cxp.domainservices.order;

import org.springframework.boot.SpringApplication;

public class TestCxpOrdermanagementDomainApplication {

	public static void main(String[] args) {
		SpringApplication.from(CxpOrdermanagementDomainApplication::main).with(ContainersConfig.class).run(args);
	}

}
