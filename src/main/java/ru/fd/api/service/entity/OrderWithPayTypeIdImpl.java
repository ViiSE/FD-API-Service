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
