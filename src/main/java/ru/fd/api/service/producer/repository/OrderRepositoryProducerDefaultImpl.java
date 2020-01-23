/*
 *  Copyright 2019 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.producer.repository;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.entity.OrderResponse;
import ru.fd.api.service.exception.ExceptionWithSendMessage;
import ru.fd.api.service.producer.entity.OrderProducer;
import ru.fd.api.service.producer.entity.OrderResponseProducer;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.entity.ProductsProducer;
import ru.fd.api.service.repository.*;

@Service("orderRepositoryProducerDefault")
public class OrderRepositoryProducerDefaultImpl implements OrderRepositoryProducer {

    private final ApplicationContext ctx;

    public OrderRepositoryProducerDefaultImpl(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public OrderRepository<Long, OrderResponse> getOrderRepositoryDeprecatedInstance(
            Order order,
            SQLQueryCreator<String, String> sqlQueryCreator,
            ProductProducer productProducer,
            ProductsProducer orderProductsProducer,
            OrderResponseProducer orderResponseProducer) {
        return (OrderRepositoryDeprecatedImpl) ctx.getBean(
                "orderRepositoryDeprecated",
                order,
                sqlQueryCreator,
                productProducer,
                orderProductsProducer,
                orderResponseProducer);
    }

    @Override
    public OrderRepository<OrderResponse, Order> getOrderRepositoryDefaultInstance(
            Order order,
            SQLQueryCreator<String, String> sqlQueryCreator,
            OrderResponseProducer orderResponseProducer) {
        return (OrderRepositoryDefaultImpl) ctx.getBean(
                "orderRepositoryDefault",
                order,
                sqlQueryCreator,
                orderResponseProducer);
    }

    @Override
    public OrderRepository<Void, Order> getOrderRepositoryChangedBalancesInstance(
            SQLQueryCreator<String, String> sqlQueryCreator,
            OrderProducer orderProducer) {
        return (OrderRepositoryChangedBalancesImpl) ctx.getBean(
                "orderRepositoryChangedBalances",
                sqlQueryCreator,
                orderProducer);
    }

    @Override
    public OrderRepository<Void, OrderResponse> getOrderRepositoryFailedInstance(
            OrderResponseProducer orderResponseProducer,
            ExceptionWithSendMessage ex) {
        return (OrderRepositoryFailedImpl) ctx.getBean("orderRepositoryFailed", orderResponseProducer, ex);
    }
}
