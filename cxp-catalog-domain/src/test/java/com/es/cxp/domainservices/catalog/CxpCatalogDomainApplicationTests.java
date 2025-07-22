package com.es.cxp.domainservices.catalog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@Import(TestContainersConfiguration.class)
@SpringBootTest(classes = com.es.cxp.domainservices.catalog.CxpCatalogDomainApplication.class)
@ActiveProfiles("test")
class CxpCatalogDomainApplicationTests {

    @Test
    void contextLoads() {}
}
