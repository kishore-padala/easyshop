package com.es.cxp.domainservices.order.clients.catalog;

import com.es.cxp.domainservices.order.ApplicationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class CatalogClientConfig {

    //    private final RestClient restClient;

    //    @Autowired
    //    public CatalogClientConfig(HttpComponentsClientHttpRequestFactory
    // customHttpRequestFactory,ApplicationProperties applicationProperties) {
    //        this.restClient = RestClient.builder()
    //                .baseUrl(applicationProperties.catalogServiceUrl())
    //                .requestFactory(customHttpRequestFactory) // Inject the custom factory
    //                .build();
    //    }

    @Bean
    public RestClient restClient(
            HttpComponentsClientHttpRequestFactory customHttpRequestFactory,
            ApplicationProperties applicationProperties) {
        return RestClient.builder()
                .baseUrl(applicationProperties.catalogDomainUrl())
                .requestFactory(customHttpRequestFactory)
                .build();
    }
}
