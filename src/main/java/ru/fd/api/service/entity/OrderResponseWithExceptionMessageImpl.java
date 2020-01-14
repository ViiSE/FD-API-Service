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
import ru.fd.api.service.data.OrderResponsePojo;

@Component("orderResponseWithExceptionMessage")
@Scope("prototype")
public class OrderResponseWithExceptionMessageImpl implements OrderResponse {

    private final OrderResponse orderResponse;
    private final String exMessage;

    public OrderResponseWithExceptionMessageImpl(OrderResponse orderResponse, String exMessage) {
        this.orderResponse = orderResponse;
        this.exMessage = exMessage;
    }

    @Override
    public Object formForSend() {
        OrderResponsePojo orderResponsePojo = (OrderResponsePojo) orderResponse.formForSend();
        orderResponsePojo.setExMessage(exMessage);
        return orderResponsePojo;
    }

    @Override
    public short status() {
        return orderResponse.status();
    }

    @Override
    public long id() {
        return orderResponse.id();
    }
}
