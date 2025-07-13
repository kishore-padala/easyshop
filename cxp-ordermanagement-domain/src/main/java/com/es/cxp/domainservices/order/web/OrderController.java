package com.es.cxp.domainservices.order.web;

import com.es.cxp.domainservices.order.model.CreateOrderRequest;
import com.es.cxp.domainservices.order.model.CreateOrderResponse;
import com.es.cxp.domainservices.order.service.OrderService;
import com.es.cxp.domainservices.order.service.SecurityService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;
    private final SecurityService securityService;

    public OrderController(OrderService orderService, SecurityService securityService) {
        this.orderService = orderService;
        this.securityService = securityService;
    }

    @PostMapping(value = "/createOrder", produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    CreateOrderResponse createOrder(@Valid @RequestBody CreateOrderRequest request) {
        String loginUserName = securityService.getLoginUserName();
        log.info("Creating order for user: {}", loginUserName);
        return orderService.createOrder(loginUserName, request);
    }
}
