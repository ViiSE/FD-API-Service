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

package ru.fd.api.service;

import ru.fd.api.service.producer.creator.CustomerCreatorProducer;
import ru.fd.api.service.producer.creator.DeliveryCreatorProducer;
import ru.fd.api.service.producer.creator.OrderCreatorProducer;
import ru.fd.api.service.producer.creator.ProductsCreatorProducer;
import ru.fd.api.service.producer.entity.*;
import ru.fd.api.service.repository.processor.OrderRepositoryProcessors;

public interface OrdersService {
    OrderCreatorProducer orderCreatorProducer();
    OrderProducer orderProducer();
    OrderRepositoryProcessors orderRepositoryProcessors();
    ProductsCreatorProducer productsCreatorProducer();
    ProductProducer productProducer();
    ProductsProducer productsProducer();
    DeliveryCreatorProducer deliveryCreatorProducer();
    CustomerCreatorProducer customerCreatorProducer();
    DeliveryProducer deliveryProducer();
    CustomerProducer customerProducer();
}
