package com.es.cxp.domainservices.order.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.Set;

public record CreateOrderRequest(
        @NotEmpty(message = "Order Items can not be empty") Set<OrderItem> items,
        @Valid Customer customer,
        @Valid Address deliveryAddress) {}
