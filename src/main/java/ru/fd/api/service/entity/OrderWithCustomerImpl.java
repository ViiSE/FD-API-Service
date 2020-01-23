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
import ru.fd.api.service.data.OrderPojo;

@Component("orderWithCustomer")
@Scope("prototype")
public class OrderWithCustomerImpl implements Order {

    private final Order order;
    private final Customer customer;

    public OrderWithCustomerImpl(Order order, Customer customer) {
        this.order = order;
        this.customer = customer;
    }

    @Override
    public Object formForSend() {
        OrderPojo orderP = (OrderPojo) order.formForSend();
        orderP.setCustomer((CustomerPojo)customer.formForSend());

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
