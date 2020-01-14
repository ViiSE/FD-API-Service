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

import ru.fd.api.service.creator.DeliveryCreator;
import ru.fd.api.service.data.DeliveryPojo;
import ru.fd.api.service.producer.entity.DeliveryProducer;

public interface DeliveryCreatorProducer {
    DeliveryCreator getDeliveryCreatorDefaultInstance(DeliveryPojo deliveryPojo, DeliveryProducer deliveryProducer);
}
