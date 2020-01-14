/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package test.producer.entity;

import ru.fd.api.service.entity.*;
import ru.fd.api.service.producer.entity.DeliveryProducer;

import java.time.LocalDate;

public class DeliveryProducerTestImpl implements DeliveryProducer {

    @Override
    public Delivery getDeliverySimpleInstance(short type, String city, String address) {
        return new DeliverySimpleImpl(type, city, address);
    }

    @Override
    public Delivery getDeliveryWithDateInstance(Delivery delivery, LocalDate deliveryDate) {
        return new DeliveryWithDateImpl(delivery, deliveryDate);
    }

    @Override
    public Delivery getDeliveryWithTimeIdInstance(Delivery delivery, short deliveryTimeId) {
        return new DeliveryWithTimeIdImpl(delivery, deliveryTimeId);
    }

    @Override
    public Delivery getDeliveryWithDepartmentIdInstance(Delivery delivery, String departmentId) {
        return new DeliveryWithDepartmentIdImpl(delivery, departmentId);
    }
}
