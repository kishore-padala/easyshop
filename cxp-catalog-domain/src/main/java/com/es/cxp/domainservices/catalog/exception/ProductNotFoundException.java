package com.es.cxp.domainservices.catalog.exception;

public class ProductNotFoundException {
    public ProductNotFoundException(String message) {
        super();
    }

    public static ProductNotFoundException forCode(String code) {
        return new ProductNotFoundException("Product with code " + code + " not found");
    }
}
