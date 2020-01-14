/*
 *  Copyright 2020 FD Company. All rights reserved.
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

@Component("createOrderRepositoryWithoutCheckStatusProcessor")
@Scope("prototype")
public class CreateOrderRepositoryWithoutCheckStatusProcessorImpl implements OrderRepositoryProcessor {

    private final OrderRepositoryProducer orderRepoProducer;
    private final SQLQueryCreator<String, String> sqlQueryCreator;
    private final OrderResponseProducer orderResponseProducer;

    public CreateOrderRepositoryWithoutCheckStatusProcessorImpl(
            OrderRepositoryProducer orderRepoProducer,
            SQLQueryCreator<String, String> sqlQueryCreator,
            OrderResponseProducer orderResponseProducer) {
        this.orderRepoProducer = orderRepoProducer;
        this.sqlQueryCreator = sqlQueryCreator;
        this.orderResponseProducer = orderResponseProducer;
    }

    @Override
    public OrderResponse apply(Order order) {
        try {
            OrderRepository<OrderResponse, Void> orderRepository = orderRepoProducer.getOrderRepositoryWithoutCheckStatusInstance(
                    order,
                    sqlQueryCreator,
                    orderResponseProducer);
            return orderRepository.insert();
        } catch (RepositoryException ex) {
            try {
                return orderRepoProducer.getOrderRepositoryFailedInstance(orderResponseProducer, ex).read(0);
            } catch (RepositoryException ignored) {}
        }

        return null;
    }
}
