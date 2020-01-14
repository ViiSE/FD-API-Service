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

import ru.fd.api.service.constant.OrderResponses;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.*;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.OrderResponseProducer;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.entity.ProductsProducer;
import ru.fd.api.service.repository.OrderRepository;

import java.util.ArrayList;

public class OrderRepositoryTestImpl implements OrderRepository<Long, OrderResponse> {

    public OrderRepositoryTestImpl(
            Order order,
            SQLQueryCreator sqlQueryCreator,
            ProductProducer productProducer,
            ProductsProducer orderProductsProducer,
            OrderResponseProducer orderResponseProducer) {
        // test stub
    }

    @Override
    public Long insert() {
        return 0L;
    }

    @Override
    public OrderResponse read(long orderId) {
        return new OrderResponseWithProductsImpl(
                new OrderResponseWithMessageImpl(
                        new OrderResponseSimpleImpl(orderId, OrderResponses.READY_TO_ASSEMBLY),
                        "Order Response message"),
                new OrderProductsDefaultImpl(new ArrayList<>() {{
                    add(new OrderProductSimpleImpl("id1", 5));
                    add(new OrderProductSimpleImpl("id2", 10));
                }}));
    }
}
