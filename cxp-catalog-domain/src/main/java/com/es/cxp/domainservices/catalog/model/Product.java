package com.es.cxp.domainservices.order.catalog.model;

import java.math.BigDecimal;

public record Product(String code, String name, String description, String imageUrl, BigDecimal price) {}
