package com.es.cxp.domainservices.order.clients.catalog;

import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

@Configuration
public class HttpClientConfig {

    @Bean
    public HttpComponentsClientHttpRequestFactory customHttpRequestFactory() {
        // Configure the HttpClient
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // Add custom configurations to httpClientBuilder if needed (e.g., connection pooling)

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(httpClientBuilder.build());
        factory.setConnectTimeout(5000); // Set connection timeout to 5 seconds
        factory.setReadTimeout(5000); // Set socket read timeout to 10 seconds
        return factory;
    }
}
