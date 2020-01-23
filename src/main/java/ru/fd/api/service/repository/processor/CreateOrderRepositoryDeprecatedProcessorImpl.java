/*
 *  Copyright 2019 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.repository.processor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.entity.OrderResponse;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.OrderResponseProducer;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.entity.ProductsProducer;
import ru.fd.api.service.producer.repository.OrderRepositoryProducer;
import ru.fd.api.service.repository.OrderRepository;

@Component("createOrderRepositoryDeprecatedProcessor")
@Scope("prototype")
@Deprecated
public class CreateOrderRepositoryDeprecatedProcessorImpl implements OrderRepositoryProcessor {

    private final OrderRepositoryProducer orderRepoProducer;
    private final SQLQueryCreator<String, String> sqlQueryCreator;
    private final ProductProducer productProducer;
    private final ProductsProducer orderProductsProducer;
    private final OrderResponseProducer orderResponseProducer;

    public CreateOrderRepositoryDeprecatedProcessorImpl(
            OrderRepositoryProducer orderRepoProducer,
            SQLQueryCreator<String, String> sqlQueryCreator,
            ProductProducer productProducer,
            ProductsProducer orderProductsProducer,
            OrderResponseProducer orderResponseProducer) {
        this.orderRepoProducer = orderRepoProducer;
        this.sqlQueryCreator = sqlQueryCreator;
        this.productProducer = productProducer;
        this.orderProductsProducer = orderProductsProducer;
        this.orderResponseProducer = orderResponseProducer;
    }

    @Override
    public Object apply(Object orderObj) {
        Order order = (Order) orderObj;

        try {
            OrderRepository<Long, OrderResponse> orderRepository = orderRepoProducer.getOrderRepositoryDeprecatedInstance(
                    order,
                    sqlQueryCreator,
                    productProducer,
                    orderProductsProducer,
                    orderResponseProducer);
            long orderCode = orderRepository.insert();
            return orderRepository.read(orderCode);
        } catch (RepositoryException ex) {
            try {
                return orderRepoProducer.getOrderRepositoryFailedInstance(orderResponseProducer, ex).read(0);
            } catch (RepositoryException ignored) {}
        }

        return null;
    }
}
