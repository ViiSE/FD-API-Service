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

import org.springframework.stereotype.Component;
import ru.fd.api.service.constant.Notifications;

@Component("customerWithNotified")
public class CustomerWithNotified implements Customer {

    private final Customer customer;
    private final short notificationStatus;
    private final long orderId;

    public CustomerWithNotified(Customer customer, short notificationStatus, long orderId) {
        this.customer = customer;
        this.notificationStatus = notificationStatus;
        this.orderId = orderId;
    }

    public boolean isNotified() {
        return notificationStatus == Notifications.IS_NOTIFIED;
    }

    public long orderId() {
        return orderId;
    }

    @Override
    public Object formForSend() {
        return null;
    }
}
