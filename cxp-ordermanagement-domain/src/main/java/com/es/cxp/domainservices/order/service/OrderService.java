package com.es.cxp.domainservices.order.service;

import com.es.cxp.domainservices.order.dao.OrderEventRepository;
import com.es.cxp.domainservices.order.dao.OrderRepository;
import com.es.cxp.domainservices.order.model.*;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private static final List<String> DELIVERY_ALLOWED_COUNTRIES = List.of("INDIA", "USA", "GERMANY", "UK");
    private final OrderRepository orderRepository;
    private final OrderValidator orderValidator;
    private final OrderEventService orderEventService;
    private final OrderEventRepository orderEventRepository;

    OrderService(
            OrderRepository orderRepository,
            OrderValidator orderValidator,
            OrderEventService orderEventService,
            OrderEventRepository orderEventRepository) {
        this.orderRepository = orderRepository;
        this.orderValidator = orderValidator;
        this.orderEventService = orderEventService;
        this.orderEventRepository = orderEventRepository;
    }

    public CreateOrderResponse createOrder(String loginUserName, CreateOrderRequest request) {
        orderValidator.validate(request);
        OrderEntity newOrder = OrderMapper.convertToEntity(request);
        Objects.requireNonNull(newOrder).setUserName(loginUserName);
        OrderEntity savedOrder = this.orderRepository.save(newOrder);
        log.info("Created Order with OrderNumber: {}", savedOrder.getOrderNumber());
        OrderCreatedEvent orderCreatedEvent = OrderEventMapper.buildOrderCreatedEvent(savedOrder);
        orderEventService.save(orderCreatedEvent);
        return new CreateOrderResponse(savedOrder.getOrderNumber());
    }

    public void processNewOrders() {
        List<OrderEntity> orders = orderRepository.findByStatus(OrderStatus.NEW);
        log.info("Found {} new orders to process", orders.size());
        for (OrderEntity order : orders) {
            this.process(order);
        }
    }

    private void process(OrderEntity order) {
        try {
            if (canBeDelivered(order)) {
                log.info("OrderNumber: {} can be delivered", order.getOrderNumber());
                orderRepository.updateOrderStatus(order.getOrderNumber(), OrderStatus.DELIVERED);
                orderEventService.save(OrderEventMapper.buildOrderDeliveredEvent(order));
            } else {
                log.warn("OrderNumber: {} cannot be delivered", order.getOrderNumber());
                orderRepository.updateOrderStatus(order.getOrderNumber(), OrderStatus.CANCELLED);
                orderEventService.save(
                        OrderEventMapper.buildOrderCancelledEvent(order, "Can't deliver to the location"));
            }
        } catch (RuntimeException e) {
            log.error("Failed to process order with OrderNumber: {}", order.getOrderNumber(), e);
            orderRepository.updateOrderStatus(order.getOrderNumber(), OrderStatus.ERROR);
            orderEventService.save(OrderEventMapper.buildOrderErrorEvent(order, e.getMessage()));
        }
    }

    private boolean canBeDelivered(OrderEntity order) {
        return DELIVERY_ALLOWED_COUNTRIES.contains(
                order.getDeliveryAddress().country().toUpperCase());
    }

    public List<OrderSummary> findOrders(String loginUserName) {
        return orderRepository.findByUserName(loginUserName);
    }

    public Optional<OrderDTO> findUserOrder(String loginUserName, String orderNumber) {
        return orderRepository
                .findByUserNameAndOrderNumber(loginUserName, orderNumber)
                .map(OrderMapper::convertToDTO);
    }
}
