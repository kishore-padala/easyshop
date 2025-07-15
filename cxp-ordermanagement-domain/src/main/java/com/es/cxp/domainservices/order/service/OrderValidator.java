package com.es.cxp.domainservices.order.service;

import com.es.cxp.domainservices.order.clients.catalog.Product;
import com.es.cxp.domainservices.order.clients.catalog.ProductServiceClient;
import com.es.cxp.domainservices.order.exception.InvalidOrderException;
import com.es.cxp.domainservices.order.model.CreateOrderRequest;
import com.es.cxp.domainservices.order.model.OrderItem;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderValidator {
    private static final Logger log = LoggerFactory.getLogger(OrderValidator.class);

    private final ProductServiceClient client;

    OrderValidator(ProductServiceClient client) {
        this.client = client;
    }

    void validate(CreateOrderRequest request) {
        Set<OrderItem> items = request.items();
        for (OrderItem item : items) {
            Product product = client.getProductByCode(item.code())
                    .orElseThrow(() -> new InvalidOrderException("Product doesn't exist with code:" + item.code()));
            if (item.price().compareTo(product.price()) != 0) {
                log.error(
                        "Product price not matching. Actual price:{}, received price:{}",
                        product.price(),
                        item.price());
                throw new InvalidOrderException("Product price not matching");
            }
        }
    }
}
