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
    public Order getOrderSimpleInstance(
            long id,
            String city,
            Customer customer,
            Delivery delivery,
            short payTypeId,
            LocalDateTime dateTime) {
        return new OrderSimpleImpl(id, city, customer, delivery, payTypeId, dateTime);
    }

    @Override
    public Order getOrderWithCommentInstance(Order order, String comment) {
        return new OrderWithCommentImpl(order, comment);
    }

    @Override
    public Order getOrderWithProductsInstance(Order order, Products products) {
        return new OrderWithProductsImpl(order, products);
    }
}
