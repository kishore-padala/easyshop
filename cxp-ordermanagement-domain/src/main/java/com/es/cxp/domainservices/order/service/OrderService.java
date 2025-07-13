package com.es.cxp.domainservices.order.service;

import com.es.cxp.domainservices.order.dao.OrderRepository;
import com.es.cxp.domainservices.order.model.CreateOrderRequest;
import com.es.cxp.domainservices.order.model.CreateOrderResponse;
import com.es.cxp.domainservices.order.model.OrderEntity;
import com.es.cxp.domainservices.order.model.OrderMapper;
import jakarta.transaction.Transactional;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final OrderValidator orderValidator;

    OrderService(OrderRepository orderRepository, OrderValidator orderValidator) {
        this.orderRepository = orderRepository;
        this.orderValidator = orderValidator;
    }

    public CreateOrderResponse createOrder(String loginUserName, CreateOrderRequest request) {
        orderValidator.validate(request);
        OrderEntity newOrder = OrderMapper.convertToEntity(request);
        Objects.requireNonNull(newOrder).setUserName(loginUserName);
        OrderEntity savedOrder = this.orderRepository.save(newOrder);
        log.info("Created Order with OrderNumber: {}", savedOrder.getOrderNumber());
        return new CreateOrderResponse(savedOrder.getOrderNumber());
    }
}
