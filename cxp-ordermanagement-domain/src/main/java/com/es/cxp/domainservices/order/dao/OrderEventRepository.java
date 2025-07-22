package com.es.cxp.domainservices.order.dao;

import com.es.cxp.domainservices.order.model.OrderEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEventRepository extends JpaRepository<OrderEventEntity, Long> {}
