/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.producer.creator;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.creator.DeliveryCreator;
import ru.fd.api.service.data.DeliveryPojo;
import ru.fd.api.service.producer.entity.DeliveryProducer;

@Service("deliveryCreatorProducerDefault")
public class DeliveryCreatorProducerDefaultImpl implements DeliveryCreatorProducer {

    private final ApplicationContext ctx;

    public DeliveryCreatorProducerDefaultImpl(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public DeliveryCreator getDeliveryCreatorDefaultInstance(DeliveryPojo deliveryPojo, DeliveryProducer deliveryProducer) {
        return (DeliveryCreator) ctx.getBean("deliveryCreatorDefault", deliveryPojo, deliveryProducer);
    }
}
