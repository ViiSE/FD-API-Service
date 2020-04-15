/*
 * Copyright 2019 ViiSE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.fd.api.service.process;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.OrderPojo;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.entity.OrderResponse;
import ru.fd.api.service.exception.ProcessException;

@Component("psChainCreateOrder")
public class PsChainCreateOrderImpl implements Process<OrderResponse, OrderPojo> {

    private final Process<Order, OrderPojo> procOrderFromBody;
    private final Process<OrderResponse, Order> procCreateOrder;

    public PsChainCreateOrderImpl(
            @Qualifier("psOrderFromBody") Process<Order, OrderPojo> procOrderFromBody,
            @Qualifier("psCreateOrder") Process<OrderResponse, Order> procCreateOrder) {
        this.procOrderFromBody = procOrderFromBody;
        this.procCreateOrder = procCreateOrder;
    }

    @Override
    public OrderResponse answer(OrderPojo orderPojo) throws ProcessException {
        Order order = procOrderFromBody.answer(orderPojo);
        return procCreateOrder.answer(order);
    }
}
