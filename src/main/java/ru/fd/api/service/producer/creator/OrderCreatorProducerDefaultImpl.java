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

package ru.fd.api.service.producer.creator;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.OrdersService;
import ru.fd.api.service.creator.OrderCreator;
import ru.fd.api.service.data.OrderPojo;

@Service("orderCreatorProducerDefault")
public class OrderCreatorProducerDefaultImpl implements OrderCreatorProducer {

    private final ApplicationContext ctx;

    public OrderCreatorProducerDefaultImpl(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public OrderCreator getOrderCreatorFromBodyInstance(OrderPojo orderPojo, OrdersService ordersService) {
        return (OrderCreator) ctx.getBean("orderCreatorFromBody", orderPojo, ordersService);
    }
}
