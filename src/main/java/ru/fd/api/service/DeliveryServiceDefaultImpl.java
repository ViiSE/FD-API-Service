/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service;

import org.springframework.stereotype.Service;
import ru.fd.api.service.producer.creator.DeliveryCreatorProducer;
import ru.fd.api.service.producer.entity.DeliveryProducer;

@Service("deliveryServiceDefault")
public class DeliveryServiceDefaultImpl implements DeliveryService {

    private final DeliveryCreatorProducer deliveryCreatorProducer;
    private final DeliveryProducer deliveryProducer;

    public DeliveryServiceDefaultImpl(DeliveryCreatorProducer deliveryCreatorProducer, DeliveryProducer deliveryProducer) {
        this.deliveryCreatorProducer = deliveryCreatorProducer;
        this.deliveryProducer = deliveryProducer;
    }

    @Override
    public DeliveryCreatorProducer deliveryCreatorProducer() {
        return deliveryCreatorProducer;
    }

    @Override
    public DeliveryProducer deliveryProducer() {
        return deliveryProducer;
    }
}
