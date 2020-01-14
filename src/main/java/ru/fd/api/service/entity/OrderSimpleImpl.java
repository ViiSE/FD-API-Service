/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
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
    private final String cityId;
    private final Customer customer;
    private final Delivery delivery;
    private final short payTypeId;
    private final LocalDateTime dateTime;

    public OrderSimpleImpl(
            long id,
            String cityId,
            Customer customer,
            Delivery delivery,
            short payTypeId,
            LocalDateTime dateTime) {
        this.id = id;
        this.cityId = cityId;
        this.customer = customer;
        this.delivery = delivery;
        this.payTypeId = payTypeId;
        this.dateTime = dateTime;
    }

    @Override
    public Object formForSend() {
        return new OrderPojo(
                id,
                cityId,
                (CustomerPojo) customer.formForSend(),
                (DeliveryPojo) delivery.formForSend(),
                payTypeId,
                dateTime);
    }
}
