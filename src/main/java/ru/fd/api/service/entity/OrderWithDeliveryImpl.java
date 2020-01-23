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

@Component("orderWithDelivery")
@Scope("prototype")
public class OrderWithDeliveryImpl implements Order {

    private final Order order;
    private final Delivery delivery;

    public OrderWithDeliveryImpl(Order order, Delivery delivery) {
        this.order = order;
        this.delivery = delivery;
    }

    @Override
    public Object formForSend() {
        OrderPojo orderP = (OrderPojo) order.formForSend();
        orderP.setDelivery((DeliveryPojo)delivery.formForSend());

        return orderP;
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
