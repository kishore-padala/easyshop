package com.es.cxp.domainservices.order.dao;

import com.es.cxp.domainservices.order.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {}
