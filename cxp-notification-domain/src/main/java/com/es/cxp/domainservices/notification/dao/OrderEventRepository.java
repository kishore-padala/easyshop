package com.es.cxp.domainservices.notification.dao;

import com.es.cxp.domainservices.notification.model.OrderEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEventRepository extends JpaRepository<OrderEventEntity, Long> {
    boolean existsByEventId(String eventId);
}
