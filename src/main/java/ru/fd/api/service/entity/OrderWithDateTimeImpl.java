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

import java.time.LocalDateTime;

@Component("orderWithDateTime")
@Scope("prototype")
public class OrderWithDateTimeImpl implements Order {

    private final Order order;
    private final LocalDateTime dateTime;

    public OrderWithDateTimeImpl(Order order, LocalDateTime dateTime) {
        this.order = order;
        this.dateTime = dateTime;
    }

    @Override
    public Object formForSend() {
        OrderPojo orderPojo = (OrderPojo) order.formForSend();
        orderPojo.setDateTime(dateTime);
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
