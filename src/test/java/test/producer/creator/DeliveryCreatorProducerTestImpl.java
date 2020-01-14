/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package test.producer.creator;

import ru.fd.api.service.creator.DeliveryCreator;
import ru.fd.api.service.creator.DeliveryCreatorDefaultImpl;
import ru.fd.api.service.data.DeliveryPojo;
import ru.fd.api.service.producer.creator.DeliveryCreatorProducer;
import ru.fd.api.service.producer.entity.DeliveryProducer;

public class DeliveryCreatorProducerTestImpl implements DeliveryCreatorProducer {

    @Override
    public DeliveryCreator getDeliveryCreatorDefaultInstance(DeliveryPojo deliveryPojo, DeliveryProducer deliveryProducer) {
        return new DeliveryCreatorDefaultImpl(deliveryPojo, deliveryProducer);
    }
}
