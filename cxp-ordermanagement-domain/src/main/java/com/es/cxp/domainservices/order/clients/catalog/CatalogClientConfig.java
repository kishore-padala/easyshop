package com.es.cxp.domainservices.order.clients.catalog;

import com.es.cxp.domainservices.order.ApplicationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
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
            RestClient.Builder builder,
            HttpComponentsClientHttpRequestFactory customHttpRequestFactory,
            ApplicationProperties applicationProperties) {
        return builder.baseUrl(applicationProperties.catalogDomainUrl())
                .requestFactory(customHttpRequestFactory)
                .build();
    }
}
