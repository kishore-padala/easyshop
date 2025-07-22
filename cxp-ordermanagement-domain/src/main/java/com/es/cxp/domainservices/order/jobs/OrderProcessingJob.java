package com.es.cxp.domainservices.order.jobs;

import com.es.cxp.domainservices.order.service.OrderService;
import java.time.Instant;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessingJob {
    private static final Logger logger = LoggerFactory.getLogger(OrderProcessingJob.class);

    private final OrderService orderService;

    public OrderProcessingJob(OrderService orderService) {
        this.orderService = orderService;
    }

    @Scheduled(cron = "${orders.new-orders-job-cron}")
    @SchedulerLock(name = "processNewOrders")
    public void processNewOrders() {
        logger.info("Processing new orders at {}", Instant.now());
        orderService.processNewOrders();
    }
}
