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

package ru.fd.api.service.producer.entity;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.entity.Customer;
import ru.fd.api.service.entity.Delivery;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.entity.Products;

import java.time.LocalDateTime;

@Service("orderProducerDefault")
public class OrderProducerDefaultImpl implements OrderProducer {

    private final ApplicationContext ctx;

    public OrderProducerDefaultImpl(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public Order getOrderSimpleInstance(long id, short status) {
        return (Order) ctx.getBean("orderSimple", id, status);
    }

    @Override
    public Order getOrderWithCityIdInstance(Order order, int cityId) {
        return (Order) ctx.getBean("orderWithCityId", order, cityId);
    }

    @Override
    public Order getOrderWithCustomerInstance(Order order, Customer customer) {
        return (Order) ctx.getBean("orderWithCustomer", order, customer);
    }

    @Override
    public Order getOrderWithDeliveryInstance(Order order, Delivery delivery) {
        return (Order) ctx.getBean("orderWithDelivery", order, delivery);
    }

    @Override
    public Order getOrderWithDateTimeInstance(Order order, LocalDateTime dateTime) {
        return (Order) ctx.getBean("orderWithDateTime", order, dateTime);
    }

    @Override
    public Order getOrderWithPayTypeIdInstance(Order order, short payTypeId) {
        return (Order) ctx.getBean("orderWithPayTypeId", order, payTypeId);
    }

    @Override
    public Order getOrderWithCommentInstance(Order order, String comment) {
        return (Order) ctx.getBean("orderWithComment", order, comment);
    }

    @Override
    public Order getOrderWithProductsInstance(Order order, Products products) {
        return (Order) ctx.getBean("orderWithProducts", order, products);
    }

    @Override
    public Order getOrderEmptyInstance() {
        return ctx.getBean("orderEmpty", Order.class);
    }
}
