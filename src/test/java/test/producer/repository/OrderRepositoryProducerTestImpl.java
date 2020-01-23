/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package test.producer.repository;

import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.entity.OrderResponse;
import ru.fd.api.service.exception.ExceptionWithSendMessage;
import ru.fd.api.service.producer.entity.OrderProducer;
import ru.fd.api.service.producer.entity.OrderResponseProducer;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.entity.ProductsProducer;
import ru.fd.api.service.producer.repository.OrderRepositoryProducer;
import ru.fd.api.service.repository.OrderRepository;
import ru.fd.api.service.repository.OrderRepositoryFailedImpl;

public class OrderRepositoryProducerTestImpl implements OrderRepositoryProducer {

    @Override
    public OrderRepository<Long, OrderResponse> getOrderRepositoryDeprecatedInstance(
            Order order,
            SQLQueryCreator<String, String> sqlQueryCreator,
            ProductProducer productProducer,
            ProductsProducer orderProductsProducer,
            OrderResponseProducer orderResponseProducer) {
        return new OrderRepositoryTestImpl(order, sqlQueryCreator, productProducer, orderProductsProducer, orderResponseProducer);
    }

    @Override
    public OrderRepository<OrderResponse, Order> getOrderRepositoryDefaultInstance(Order order, SQLQueryCreator<String, String> sqlQueryCreator, OrderResponseProducer orderResponseProducer) {
        return null;
    }

    @Override
    public OrderRepository<Void, Order> getOrderRepositoryChangedBalancesInstance(SQLQueryCreator<String, String> sqlQueryCreator, OrderProducer orderProducer) {
        return null;
    }

    @Override
    public OrderRepository<Void, OrderResponse> getOrderRepositoryFailedInstance(OrderResponseProducer orderResponseProducer, ExceptionWithSendMessage ex) {
        return new OrderRepositoryFailedImpl(orderResponseProducer, ex);
    }

}
