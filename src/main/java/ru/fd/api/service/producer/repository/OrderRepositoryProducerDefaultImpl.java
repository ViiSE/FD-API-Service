/*
 *  Copyright 2019 ViiSE.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package ru.fd.api.service.producer.repository;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.entity.OrderResponse;
import ru.fd.api.service.exception.ExceptionWithSendMessage;
import ru.fd.api.service.producer.entity.OrderResponseProducer;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.entity.ProductsProducer;
import ru.fd.api.service.repository.OrderRepository;
import ru.fd.api.service.repository.OrderRepositoryDefaultImpl;
import ru.fd.api.service.repository.OrderRepositoryFailedImpl;
import ru.fd.api.service.repository.OrderRepositoryWithoutCheckStatusImpl;

@Service("orderRepositoryProducerDefault")
public class OrderRepositoryProducerDefaultImpl implements OrderRepositoryProducer {

    private final ApplicationContext ctx;

    public OrderRepositoryProducerDefaultImpl(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public OrderRepository<Long, OrderResponse> getOrderRepositoryDefaultInstance(
            Order order,
            SQLQueryCreator<String, String> sqlQueryCreator,
            ProductProducer productProducer,
            ProductsProducer orderProductsProducer,
            OrderResponseProducer orderResponseProducer) {
        return (OrderRepositoryDefaultImpl) ctx.getBean(
                "orderRepositoryDefault",
                order,
                sqlQueryCreator,
                productProducer,
                orderProductsProducer,
                orderResponseProducer);
    }

    @Override
    public OrderRepository<OrderResponse, Void> getOrderRepositoryWithoutCheckStatusInstance(
            Order order,
            SQLQueryCreator<String, String> sqlQueryCreator,
            OrderResponseProducer orderResponseProducer) {
        return (OrderRepositoryWithoutCheckStatusImpl) ctx.getBean("orderRepositoryWithoutCheckStatus",
                order,
                sqlQueryCreator,
                orderResponseProducer);
    }

    @Override
    public OrderRepository<Void, OrderResponse> getOrderRepositoryFailedInstance(OrderResponseProducer orderResponseProducer, ExceptionWithSendMessage ex) {
        return (OrderRepositoryFailedImpl) ctx.getBean("orderRepositoryFailed", orderResponseProducer, ex);
    }
}
