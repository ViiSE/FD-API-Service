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
import ru.fd.api.service.data.CustomerPojo;
import ru.fd.api.service.data.DeliveryPojo;
import ru.fd.api.service.data.OrderPojo;

import java.time.LocalDateTime;

@Component("orderSimple")
@Scope("prototype")
public class OrderSimpleImpl implements Order {

    private final long id;
    private final String city;
    private final CustomerPojo customer;
    private final DeliveryPojo delivery;
    private final short payTypeId;
    private final LocalDateTime dateTime;

    public OrderSimpleImpl(
            long id,
            String city,
            CustomerPojo customer,
            DeliveryPojo delivery,
            short payTypeId,
            LocalDateTime dateTime) {
        this.id = id;
        this.city = city;
        this.customer = customer;
        this.delivery = delivery;
        this.payTypeId = payTypeId;
        this.dateTime = dateTime;
    }

    @Override
    public Object formForSend() {
        return new OrderPojo(id, city, customer, delivery, payTypeId, dateTime);
    }
}
