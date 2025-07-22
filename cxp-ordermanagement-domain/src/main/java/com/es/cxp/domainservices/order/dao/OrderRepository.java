package com.es.cxp.domainservices.order.dao;

import com.es.cxp.domainservices.order.model.OrderEntity;
import com.es.cxp.domainservices.order.model.OrderStatus;
import com.es.cxp.domainservices.order.model.OrderSummary;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByStatus(OrderStatus orderStatus);

    default void updateOrderStatus(String orderNumber, OrderStatus orderStatus) {
        OrderEntity orderEntity = this.findByOrderNumber(orderNumber).orElseThrow();
        orderEntity.setStatus(orderStatus);
        this.save(orderEntity);
    }

    Optional<OrderEntity> findByOrderNumber(String orderNumber);

    @Query(
            """
            select new com.es.cxp.domainservices.order.model.OrderSummary(o.orderNumber, o.status) from OrderEntity o
            where o.userName = :loginUserName
            """)
    List<OrderSummary> findByUserName(String loginUserName);

    @Query(
            """
            select distinct o from OrderEntity o left join fetch o.items
            where o.userName = :loginUserName and o.orderNumber = :orderNumber
            """)
    Optional<OrderEntity> findByUserNameAndOrderNumber(String loginUserName, String orderNumber);
}
