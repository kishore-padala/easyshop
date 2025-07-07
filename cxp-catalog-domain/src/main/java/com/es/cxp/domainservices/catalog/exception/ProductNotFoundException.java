package com.es.cxp.domainservices.order.catalog.exception;

public class ProductNotFoundException extends RuntimeException {
    private ProductNotFoundException(String message) {
        super(message);
    }

    public static ProductNotFoundException forCode(String code) {
        return new ProductNotFoundException("Product with code " + code + " not found");
    }
}
