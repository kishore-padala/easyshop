package com.es.cxp.domainservices.notification.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record OrderItem(
        @NotBlank(message = "code can not be empty") String code,
        @NotBlank(message = "name can not be empty") String name,
        @NotNull(message = "price can not be empty") BigDecimal price,
        @NotNull @Min(1) Integer quantity) {}
