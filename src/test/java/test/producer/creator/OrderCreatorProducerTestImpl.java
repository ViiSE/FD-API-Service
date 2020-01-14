/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package test.producer.creator;

import ru.fd.api.service.OrdersService;
import ru.fd.api.service.creator.OrderCreator;
import ru.fd.api.service.creator.OrderCreatorFromBodyImpl;
import ru.fd.api.service.data.OrderPojo;
import ru.fd.api.service.producer.creator.OrderCreatorProducer;

public class OrderCreatorProducerTestImpl implements OrderCreatorProducer {

    @Override
    public OrderCreator getOrderCreatorFromBodyInstance(OrderPojo orderPojo, OrdersService ordersService) {
        return new OrderCreatorFromBodyImpl(orderPojo, ordersService);
    }
}
