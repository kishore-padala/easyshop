package com.es.cxp.domainservices.order.clients.catalog;

import io.github.resilience4j.retry.annotation.Retry;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ProductServiceClient {
    private static final Logger log = LoggerFactory.getLogger(ProductServiceClient.class);

    private final RestClient restClient;

    public ProductServiceClient(RestClient restClient) {
        this.restClient = restClient;
    }

    //    @CircuitBreaker(name = "catalog-domain")
    @Retry(name = "catalog-domain", fallbackMethod = "getProductByCodeFallback")
    public Optional<Product> getProductByCode(String code) {
        log.info("Fetching product by code: {}", code);
        //        try {
        var product =
                restClient.get().uri("/api/products/{code}", code).retrieve().body(Product.class);
        return Optional.ofNullable(product);
        //        } catch (Exception e) {
        //            log.error("Error fetching product by code: {}", code, e);
        //            return Optional.empty();
        //        }
    }

    Optional<Product> getProductByCodeFallback(String code, Throwable throwable) {
        log.warn("Fallback for getProductByCode called for code: {}, due to: {}", code, throwable.getMessage());
        return Optional.empty();
    }
}
