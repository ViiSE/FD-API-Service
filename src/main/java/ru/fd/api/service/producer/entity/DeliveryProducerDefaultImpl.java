/*
 *  Copyright 2019 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.producer.entity;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.entity.Delivery;

import java.time.LocalDate;

@Service("deliveryProducerDefault")
public class DeliveryProducerDefaultImpl implements DeliveryProducer {

    private final ApplicationContext ctx;

    public DeliveryProducerDefaultImpl(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public Delivery getDeliverySimpleInstance(short type, int cityId, String address) {
        return (Delivery) ctx.getBean("deliverySimple", type, cityId, address);
    }

    @Override
    public Delivery getDeliveryWithDateInstance(Delivery delivery, LocalDate deliveryDate) {
        return (Delivery) ctx.getBean("deliveryWithDate", delivery, deliveryDate);
    }

    @Override
    public Delivery getDeliveryWithTimeIdInstance(Delivery delivery, short deliveryTimeId) {
        return (Delivery) ctx.getBean("deliveryWithTimeId", delivery, deliveryTimeId);
    }

    @Override
    public Delivery getDeliveryWithDepartmentIdInstance(Delivery delivery, String departmentId) {
        return (Delivery) ctx.getBean("deliveryWithDepartmentId", delivery, departmentId);
    }
}
