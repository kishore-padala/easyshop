package com.es.cxp.domainservices.cxp_catalog_domain;

import org.springframework.boot.SpringApplication;

public class TestCxpCatalogDomainApplication {

	public static void main(String[] args) {
		SpringApplication.from(CxpCatalogDomainApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
