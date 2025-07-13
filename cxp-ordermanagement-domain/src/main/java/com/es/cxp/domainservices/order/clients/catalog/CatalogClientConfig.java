package com.es.cxp.domainservices.order.clients.catalog;

import com.es.cxp.domainservices.order.ApplicationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class CatalogClientConfig {

    @Bean
    RestClient restClient(ApplicationProperties applicationProperties) {
        return RestClient.builder()
                .baseUrl(applicationProperties.catalogServiceUrl())
                .build();
    }
}
