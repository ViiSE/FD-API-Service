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

package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.OrderPojo;

@Component("orderWithPayTypeId")
@Scope("prototype")
public class OrderWithPayTypeIdImpl implements Order {

    private final Order order;
    private final short payTypeId;

    public OrderWithPayTypeIdImpl(Order order, short payTypeId) {
        this.order = order;
        this.payTypeId = payTypeId;
    }

    @Override
    public Object formForSend() {
        OrderPojo orderPojo = (OrderPojo) order.formForSend();
        orderPojo.setPayTypeId(payTypeId);
        return orderPojo;
    }

    @Override
    public long id() {
        return order.id();
    }

    @Override
    public short status() {
        return order.status();
    }
}
