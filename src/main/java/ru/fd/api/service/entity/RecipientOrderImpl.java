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

import java.util.ArrayList;
import java.util.List;

@Component("recipientOrder")
@Scope("prototype")
public class RecipientOrderImpl implements Recipient {

    private final long siteId;
    private final Order order;

    public RecipientOrderImpl(Order order, long siteId) {
        this.order = order;
        this.siteId = siteId;
    }

    @Override
    public List<String> addresses() {
        List<String> addresses = new ArrayList<>();

        OrderPojo orderPojo = (OrderPojo) order.formForSend();
        String email = orderPojo.getCustomer().getEmail();
        String phoneNumber = orderPojo.getCustomer().getPhoneNumber();

        if(!email.isEmpty())
            addresses.add(email);

        if(!phoneNumber.isEmpty())
            addresses.add(phoneNumber);

        return addresses;
    }


}
