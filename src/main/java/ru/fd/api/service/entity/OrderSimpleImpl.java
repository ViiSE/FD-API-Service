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
