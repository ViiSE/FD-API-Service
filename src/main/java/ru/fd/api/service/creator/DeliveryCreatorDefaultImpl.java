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

package ru.fd.api.service.creator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.fd.api.service.data.DeliveryPojo;
import ru.fd.api.service.entity.Delivery;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.producer.entity.DeliveryProducer;

import java.time.LocalDate;

@Service("deliveryCreatorDefault")
@Scope("prototype")
public class DeliveryCreatorDefaultImpl implements DeliveryCreator {

    private final DeliveryPojo deliveryPojo;
    private final DeliveryProducer deliveryProducer;

    public DeliveryCreatorDefaultImpl(DeliveryPojo deliveryPojo, DeliveryProducer deliveryProducer) {
        this.deliveryPojo = deliveryPojo;
        this.deliveryProducer = deliveryProducer;
    }

    @Override
    public Delivery create() throws CreatorException {
        checkDelivery();

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

    private void checkDelivery() throws CreatorException {
        if(deliveryPojo == null)
            throw new CreatorException("Delivery required");

        if(deliveryPojo.getCityId().isEmpty())
            throw new CreatorException("Delivery: city id required");

        if(deliveryPojo.getAddress().isEmpty())
            throw new CreatorException("Delivery: address required");

        if(deliveryPojo.getDepartmentId().isEmpty())
            throw new CreatorException("Delivery: department id required");

        if(deliveryPojo.getDeliveryDate().equals(LocalDate.MIN))
            throw new CreatorException("Delivery: delivery date required");
    }
}