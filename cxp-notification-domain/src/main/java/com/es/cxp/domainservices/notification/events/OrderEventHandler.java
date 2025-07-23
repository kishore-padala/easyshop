package com.es.cxp.domainservices.notification.events;

import com.es.cxp.domainservices.notification.dao.OrderEventRepository;
import com.es.cxp.domainservices.notification.model.*;
import com.es.cxp.domainservices.notification.service.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Handles all order-related events from RabbitMQ.
 */
@Component
class OrderEventHandler {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(OrderEventHandler.class);

    private final NotificationService notificationService;
    private final OrderEventRepository orderEventRepository;

    OrderEventHandler(NotificationService notificationService, OrderEventRepository orderEventRepository) {
        this.notificationService = notificationService;
        this.orderEventRepository = orderEventRepository;
    }

    @RabbitListener(queues = "${notifications.new-orders-queue}")
    public void handleOrderCreatedEvent(OrderCreatedEvent event) {
        try {
            log.info("Received OrderCreatedEvent for orderNumber: {}", event.orderNumber());
            if (orderEventRepository.existsByEventId(event.eventId())) {
                log.warn("Received duplicate OrderCreatedEvent with eventId: {}", event.eventId());
                return;
            }
            notificationService.sendOrderCreatedNotification(event);
            OrderEventEntity orderEventEntity = new OrderEventEntity(event.eventId());
            orderEventRepository.save(orderEventEntity);
            log.info("Successfully processed OrderCreatedEvent for orderNumber: {}", event.orderNumber());
        } catch (Exception e) {
            log.error("Error processing OrderCreatedEvent: {}", e.getMessage(), e);
            throw e; // Re-throw to trigger retry mechanism
        }
    }

    @RabbitListener(queues = "${notifications.delivered-orders-queue}")
    public void handleOrderDeliveredEvent(OrderDeliveredEvent event) {
        try {
            log.info("Received OrderDeliveredEvent for orderNumber: {}", event.orderNumber());
            if (orderEventRepository.existsByEventId(event.eventId())) {
                log.warn("Received duplicate OrderDeliveredEvent with eventId: {}", event.eventId());
                return;
            }
            notificationService.sendOrderDeliveredNotification(event);
            OrderEventEntity orderEventEntity = new OrderEventEntity(event.eventId());
            orderEventRepository.save(orderEventEntity);
            log.info("Successfully processed OrderDeliveredEvent for orderNumber: {}", event.orderNumber());
        } catch (Exception e) {
            log.error("Error processing OrderDeliveredEvent: {}", e.getMessage(), e);
            throw e; // Re-throw to trigger retry mechanism
        }
    }

    @RabbitListener(queues = "${notifications.cancelled-orders-queue}")
    public void handleOrderCancelledEvent(OrderCancelledEvent event) {
        try {
            log.info("Received OrderCancelledEvent for orderNumber: {}", event.orderNumber());
            if (orderEventRepository.existsByEventId(event.eventId())) {
                log.warn("Received duplicate OrderCancelledEvent with eventId: {}", event.eventId());
                return;
            }
            notificationService.sendOrderCancelledNotification(event);
            OrderEventEntity orderEventEntity = new OrderEventEntity(event.eventId());
            orderEventRepository.save(orderEventEntity);
            log.info("Successfully processed OrderCancelledEvent for orderNumber: {}", event.orderNumber());
        } catch (Exception e) {
            log.error("Error processing OrderCancelledEvent: {}", e.getMessage(), e);
            throw e; // Re-throw to trigger retry mechanism
        }
    }

    @RabbitListener(queues = "${notifications.error-orders-queue}")
    public void handleOrderErrorEvent(OrderErrorEvent event) {
        try {
            log.error("Received OrderErrorEvent for order: {}, Reason: {}", event.orderNumber(), event.reason());
            if (orderEventRepository.existsByEventId(event.eventId())) {
                log.warn("Received duplicate OrderErrorEvent with eventId: {}", event.eventId());
                return;
            }
            notificationService.sendOrderErrorEventNotification(event);
            OrderEventEntity orderEventEntity = new OrderEventEntity(event.eventId());
            orderEventRepository.save(orderEventEntity);
            log.info("Successfully processed OrderErrorEvent for orderNumber: {}", event.orderNumber());
        } catch (Exception e) {
            log.error("Error processing OrderErrorEvent: {}", e.getMessage(), e);
        }
    }
}
