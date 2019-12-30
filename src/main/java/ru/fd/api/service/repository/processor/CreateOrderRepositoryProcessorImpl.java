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

package ru.fd.api.service.repository.processor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.entity.OrderResponse;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.repository.OrderRepositoryProducer;
import ru.fd.api.service.repository.OrderRepository;

@Component("createOrderRepositoryProcessor")
@Scope("prototype")
public class CreateOrderRepositoryProcessorImpl implements OrderRepositoryProcessor {

    private final OrderRepositoryProducer orderRepoProducer;
    private final SQLQueryCreator<String, String> sqlQueryCreator;

    public CreateOrderRepositoryProcessorImpl(OrderRepositoryProducer orderRepoProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        this.orderRepoProducer = orderRepoProducer;
        this.sqlQueryCreator = sqlQueryCreator;
    }
//return OrderResponseProducer.getOrderResponseSuccessImpl(order);

    @Override
    public OrderResponse apply(Order order) {
        try {
            OrderRepository orderRepository = orderRepoProducer.getOrderRepositoryDefaultInstance(order, sqlQueryCreator);
            long orderCode = orderRepository.insert();
            return orderRepository.read(orderCode);
        } catch (RepositoryException ex) {
            return orderRepoProducer.getOrderRepositoryFailedInstance(ex).read();
        }
    }
}
