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

package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.OrderResponsePojo;

@Component("orderResponseSimple")
@Scope("prototype")
public class OrderResponseSimpleImpl implements OrderResponse {

    private final long id;
    private final short status;

    public OrderResponseSimpleImpl(long id, short status) {
        this.id = id;
        this.status = status;
    }

    @Override
    public Object formForSend() {
        OrderResponsePojo orderResponsePojo = new OrderResponsePojo();
        orderResponsePojo.setId(id);
        orderResponsePojo.setStatus(status);
        return orderResponsePojo;
    }

    @Override
    public short status() {
        return status;
    }

    @Override
    public long id() {
        return id;
    }
}
