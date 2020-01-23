/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package test.producer.entity;

import ru.fd.api.service.entity.*;
import ru.fd.api.service.producer.entity.OrderProducer;

import java.time.LocalDateTime;

public class OrderProducerTestImpl implements OrderProducer {

    @Override
    public Order getOrderSimpleInstance(long id, short status) {
        return new OrderSimpleImpl(id, status);
    }

    @Override
    public Order getOrderWithCityIdInstance(Order order, int cityId) {
        return new OrderWithCityIdImpl(order, cityId);
    }

    @Override
    public Order getOrderWithCustomerInstance(Order order, Customer customer) {
        return new OrderWithCustomerImpl(order, customer);
    }

    @Override
    public Order getOrderWithDeliveryInstance(Order order, Delivery delivery) {
        return new OrderWithDeliveryImpl(order, delivery);
    }

    @Override
    public Order getOrderWithDateTimeInstance(Order order, LocalDateTime dateTime) {
        return new OrderWithDateTimeImpl(order, dateTime);
    }

    @Override
    public Order getOrderWithPayTypeIdInstance(Order order, short payTypeId) {
        return new OrderWithPayTypeIdImpl(order, payTypeId);
    }

    @Override
    public Order getOrderWithCommentInstance(Order order, String comment) {
        return new OrderWithCommentImpl(order, comment);
    }

    @Override
    public Order getOrderWithProductsInstance(Order order, Products products) {
        return new OrderWithProductsImpl(order, products);
    }

    @Override
    public Order getOrderEmptyInstance() {
        return null;
    }
}
