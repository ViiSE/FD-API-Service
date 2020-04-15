/*
 *  Copyright 2019 ViiSE.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package ru.fd.api.service.process;

import org.springframework.stereotype.Component;
import ru.fd.api.service.data.DeliveryPojo;
import ru.fd.api.service.entity.Delivery;
import ru.fd.api.service.exception.ProcessException;
import ru.fd.api.service.producer.entity.DeliveryProducer;

import java.time.LocalDate;

@Component("psDelivery")
public class PsDeliveryImpl implements Process<Delivery, DeliveryPojo> {

    private final DeliveryProducer deliveryProducer;

    public PsDeliveryImpl(DeliveryProducer deliveryProducer) {
        this.deliveryProducer = deliveryProducer;
    }

    @Override
    public Delivery answer(DeliveryPojo deliveryPojo) throws ProcessException {
        checkDelivery(deliveryPojo);

        return deliveryProducer.getDeliveryWithDateInstance(
                deliveryProducer.getDeliveryWithTimeIdInstance(
                        deliveryProducer.getDeliveryWithDepartmentIdInstance(
                                deliveryProducer.getDeliverySimpleInstance(
                                        deliveryPojo.getType(),
                                        deliveryPojo.getCityId(),
                                        deliveryPojo.getAddress()),
                                deliveryPojo.getDepartmentId()),
                        deliveryPojo.getDeliveryTimeId()),
                deliveryPojo.getDeliveryDate());
    }

    private void checkDelivery(DeliveryPojo deliveryPojo) throws ProcessException {
        // TODO: 16.01.2020 CREATE CHECKER INTERFACE

        if(deliveryPojo == null)
            throw new ProcessException("Delivery required");

        if(deliveryPojo.getType() != 0 && deliveryPojo.getType() != 1)
            throw new ProcessException("Delivery: unknown type");

        if(deliveryPojo.getCityId() < 0)
            throw new ProcessException("Delivery: city id required");

        if(deliveryPojo.getType() == 0) {
            if (deliveryPojo.getDepartmentId() == null)
                throw new ProcessException("Delivery: department id required");

            if (deliveryPojo.getDepartmentId().isEmpty())
                    throw new ProcessException("Delivery: department id required");
        } else if(deliveryPojo.getType() == 1) {
            if(deliveryPojo.getAddress().isEmpty())
                throw new ProcessException("Delivery: address required");

            if (deliveryPojo.getDeliveryDate().equals(LocalDate.of(1, 1, 1)))
                throw new ProcessException("Delivery: delivery date required");

            if(deliveryPojo.getDeliveryTimeId() != 0 && deliveryPojo.getDeliveryTimeId() != 1) {
                throw new ProcessException("Delivery: unknown delivery time id");
            }
        }
    }
}
