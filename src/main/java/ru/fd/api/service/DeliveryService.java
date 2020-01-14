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

import ru.fd.api.service.producer.creator.DeliveryCreatorProducer;
import ru.fd.api.service.producer.entity.DeliveryProducer;

public interface DeliveryService {
    DeliveryCreatorProducer deliveryCreatorProducer();
    DeliveryProducer deliveryProducer();
}
