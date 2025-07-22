package com.es.cxp.domainservices.order;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(ContainersConfig.class)
@SpringBootTest(classes = com.es.cxp.domainservices.order.CxpOrdermanagementDomainApplication.class)
class CxpOrdermanagementDomainApplicationTests {

    @Test
    void contextLoads() {}
}
