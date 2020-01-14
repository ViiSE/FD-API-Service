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
import ru.fd.api.service.producer.entity.OrderResponseProducer;

public class OrderResponseProducerTestImpl implements OrderResponseProducer {

    @Override
    public OrderResponse getOrderResponseSimpleInstance(long id, short status) {
        return new OrderResponseSimpleImpl(id, status);
    }

    @Override
    public OrderResponse getOrderResponseWithMessageInstance(OrderResponse response, String message) {
        return new OrderResponseWithMessageImpl(response, message);
    }

    @Override
    public OrderResponse getOrderResponseWithProductsInstance(OrderResponse response, Products orderProducts) {
        return new OrderResponseWithProductsImpl(response, orderProducts);
    }

    @Override
    public OrderResponse getOrderResponseWithExceptionMessageInstance(OrderResponse response, String exMessage) {
        return new OrderResponseWithExceptionMessageImpl(response, exMessage);
    }
}
