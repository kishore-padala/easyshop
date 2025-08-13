package com.es.easyshop.webapp.clients.orders;

public record OrderConfirmationDTO(String orderNumber, OrderStatus status) {}
