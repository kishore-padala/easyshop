package com.es.cxp.domainservices.order.model;

import java.time.LocalDateTime;
import java.util.Set;

public class OrderDTO {
    private String orderNumber;
    private String userName;
    private Set<OrderItem> items;
    private Customer customer;
    private Address deliveryAddress;
    private OrderStatus status;
    private String comments;
    private LocalDateTime createdAt;

    // Constructor
    public OrderDTO(
            String orderNumber,
            String userName,
            Set<OrderItem> items,
            Customer customer,
            Address deliveryAddress,
            OrderStatus status,
            String comments,
            LocalDateTime createdAt) {
        this.orderNumber = orderNumber;
        this.userName = userName;
        this.items = items;
        this.customer = customer;
        this.deliveryAddress = deliveryAddress;
        this.status = status;
        this.comments = comments;
        this.createdAt = createdAt;
    }

    // Getters and setters
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
