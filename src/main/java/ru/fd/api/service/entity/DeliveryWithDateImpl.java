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
import ru.fd.api.service.data.DeliveryPojo;

import java.time.LocalDate;

@Component("deliveryWithDate")
@Scope("prototype")
public class DeliveryWithDateImpl implements Delivery {

    private final Delivery delivery;
    private final LocalDate deliveryDate;

    public DeliveryWithDateImpl(Delivery delivery, LocalDate deliveryDate) {
        this.delivery = delivery;
        this.deliveryDate = deliveryDate;
    }

    @Override
    public Object formForSend() {
        DeliveryPojo deliveryPojo = (DeliveryPojo) delivery.formForSend();
        deliveryPojo.setDeliveryDate(deliveryDate);
        return deliveryPojo;
    }
}
