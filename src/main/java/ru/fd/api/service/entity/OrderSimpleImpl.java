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

@Component("orderSimple")
@Scope("prototype")
public class OrderSimpleImpl implements Order {

    private final long id;
    private final short status;

    public OrderSimpleImpl(long id, short status) {
        this.id = id;
        this.status = status;
    }

    @Override
    public Object formForSend() {
        return new OrderPojo(id) {{ setStatus(status); }};
    }

    @Override
    public long id() {
        return id;
    }

    @Override
    public short status() {
        return status;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Order) {
            Order orderE = (Order) obj;
            return this.id == orderE.id();
        }
        return super.equals(obj);
    }
}
