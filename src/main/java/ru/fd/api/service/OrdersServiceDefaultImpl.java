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

import org.springframework.stereotype.Service;
import ru.fd.api.service.producer.creator.CustomerCreatorProducer;
import ru.fd.api.service.producer.creator.DeliveryCreatorProducer;
import ru.fd.api.service.producer.creator.OrderCreatorProducer;
import ru.fd.api.service.producer.creator.ProductsCreatorProducer;
import ru.fd.api.service.producer.entity.*;
import ru.fd.api.service.repository.processor.OrderRepositoryProcessor;
import ru.fd.api.service.repository.processor.OrderRepositoryProcessors;

@Service("orderServiceDefault")
public class OrdersServiceDefaultImpl implements OrdersService {

    private final OrderCreatorProducer orderCreatorProducer;
    private final OrderProducer orderProducer;
    private final OrderRepositoryProcessors orderRepositoryProcessors;
    private final ProductsService productsService;
    private final CustomerService customerService;
    private final DeliveryService deliveryService;

    public OrdersServiceDefaultImpl(
            OrderCreatorProducer orderCreatorProducer,
            OrderProducer orderProducer,
            OrderRepositoryProcessors orderRepositoryProcessors,
            ProductsService productsService,
            CustomerService customerService,
            DeliveryService deliveryService) {
        this.orderCreatorProducer = orderCreatorProducer;
        this.orderProducer = orderProducer;
        this.productsService = productsService;
        this.orderRepositoryProcessors = orderRepositoryProcessors;
        this.customerService = customerService;
        this.deliveryService = deliveryService;
    }

    @Override
    public OrderCreatorProducer orderCreatorProducer() {
        return orderCreatorProducer;
    }

    @Override
    public OrderProducer orderProducer() {
        return orderProducer;
    }

    @Override
    public ProductsCreatorProducer productsCreatorProducer() {
        return productsService.productsCreatorProducer();
    }

    @Override
    public ProductProducer productProducer() {
        return productsService.productProducer();
    }

    @Override
    public ProductsProducer productsProducer() {
        return productsService.productsProducer();
    }

    @Override
    public DeliveryCreatorProducer deliveryCreatorProducer() {
        return deliveryService.deliveryCreatorProducer();
    }

    @Override
    public CustomerCreatorProducer customerCreatorProducer() {
        return customerService.customerCreatorProducer();
    }

    @Override
    public DeliveryProducer deliveryProducer() {
        return deliveryService.deliveryProducer();
    }

    @Override
    public CustomerProducer customerProducer() {
        return customerService.customerProducer();
    }

    @Override
    public OrderRepositoryProcessors orderRepositoryProcessors() {
        return orderRepositoryProcessors;
    }
}
