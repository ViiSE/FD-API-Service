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

@Component("orderWithPrimaryKey")
@Scope("prototype")
public class OrderWithPrimaryKeyImpl implements Order {

    private final Order order;
    private final long pk;

    public OrderWithPrimaryKeyImpl(Order order, long pk) {
        this.order = order;
        this.pk = pk;
    }

    @Override
    public Object formForSend() {
        OrderPojo orderPojo = (OrderPojo) order.formForSend();
        orderPojo.setPk(pk);
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
