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

@Component("deliveryWithTimeId")
@Scope("prototype")
public class DeliveryWithTimeIdImpl implements Delivery {

    private final Delivery delivery;
    private final short deliveryTimeId;

    public DeliveryWithTimeIdImpl(Delivery delivery, short deliveryTimeId) {
        this.delivery = delivery;
        this.deliveryTimeId = deliveryTimeId;
    }

    @Override
    public Object formForSend() {
        DeliveryPojo deliveryPojo = (DeliveryPojo) delivery.formForSend();
        deliveryPojo.setDeliveryTimeId(deliveryTimeId);
        return deliveryPojo;
    }
}
