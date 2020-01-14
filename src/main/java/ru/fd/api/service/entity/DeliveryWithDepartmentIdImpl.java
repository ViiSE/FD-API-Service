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

@Component("deliveryWithDepartmentId")
@Scope("prototype")
public class DeliveryWithDepartmentIdImpl implements Delivery {

    private final Delivery delivery;
    private final String departmentId;

    public DeliveryWithDepartmentIdImpl(Delivery delivery, String departmentId) {
        this.delivery = delivery;
        this.departmentId = departmentId;
    }

    @Override
    public Object formForSend() {
        DeliveryPojo deliveryPojo = (DeliveryPojo) delivery.formForSend();
        deliveryPojo.setDepartmentId(departmentId);
        return deliveryPojo;
    }
}
