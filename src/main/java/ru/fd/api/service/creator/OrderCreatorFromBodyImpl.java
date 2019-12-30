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

package ru.fd.api.service.creator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.fd.api.service.data.OrderPojo;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.producer.creator.ProductsCreatorProducer;
import ru.fd.api.service.producer.entity.OrderProducer;

@Service("orderCreatorFromBody")
@Scope("prototype")
public class OrderCreatorFromBodyImpl implements OrderCreator {

    private final OrderPojo orderPojo;
    private final OrderProducer orderProducer;
    private final ProductsCreatorProducer productsCreatorProducer;

    public OrderCreatorFromBodyImpl(
            OrderPojo orderPojo,
            OrderProducer orderProducer,
            ProductsCreatorProducer productsCreatorProducer) {
        this.orderPojo = orderPojo;
        this.orderProducer = orderProducer;
        this.productsCreatorProducer = productsCreatorProducer;
    }

    @Override
    public Order create() throws CreatorException {
        Order order = orderProducer.getOrderSimpleInstance(
                orderPojo.getId(),
                orderPojo.getCityId(),
                orderPojo.getCustomer(),
                orderPojo.getDelivery(),
                orderPojo.getPayTypeId(),
                orderPojo.getDateTime());

        if(orderPojo.getProducts() != null)
            order = orderProducer.getOrderWithProductsInstance(
                    order,
                    productsCreatorProducer.getProductsOrderCreatorInstance(orderPojo).create());
        else
            throw new CreatorException("Products required");

        if(orderPojo.getComment().isEmpty())
            return order;
        else
            return orderProducer.getOrderWithCommentInstance(order, orderPojo.getComment());
    }
}
