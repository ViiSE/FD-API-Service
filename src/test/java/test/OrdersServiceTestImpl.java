/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package test;

import ru.fd.api.service.OrdersService;
import ru.fd.api.service.producer.creator.CustomerCreatorProducer;
import ru.fd.api.service.producer.creator.DeliveryCreatorProducer;
import ru.fd.api.service.producer.creator.OrderCreatorProducer;
import ru.fd.api.service.producer.creator.ProductsCreatorProducer;
import ru.fd.api.service.producer.entity.*;
import ru.fd.api.service.repository.processor.OrderRepositoryProcessors;
import test.creator.CustomerProducerTestImpl;
import test.producer.creator.CustomerCreatorProducerTestImpl;
import test.producer.creator.DeliveryCreatorProducerTestImpl;
import test.producer.creator.OrderCreatorProducerTestImpl;
import test.producer.creator.ProductsCreatorProducerTestImpl;
import test.producer.entity.DeliveryProducerTestImpl;
import test.producer.entity.OrderProducerTestImpl;
import test.producer.entity.ProductProducerTestImpl;
import test.producer.entity.ProductsProducerTestImpl;

public class OrdersServiceTestImpl implements OrdersService {

    @Override
    public OrderCreatorProducer orderCreatorProducer() {
        return new OrderCreatorProducerTestImpl();
    }

    @Override
    public OrderProducer orderProducer() {
        return new OrderProducerTestImpl();
    }

    @Override
    public OrderRepositoryProcessors orderRepositoryProcessors() {
        // TODO: 09.01.2020 CREATE IMPLEMENTATION
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public ProductsCreatorProducer productsCreatorProducer() {
        return new ProductsCreatorProducerTestImpl();
    }

    @Override
    public ProductProducer productProducer() {
        return new ProductProducerTestImpl();
    }

    @Override
    public ProductsProducer productsProducer() {
        return new ProductsProducerTestImpl();
    }

    @Override
    public DeliveryCreatorProducer deliveryCreatorProducer() {
        return new DeliveryCreatorProducerTestImpl();
    }

    @Override
    public CustomerCreatorProducer customerCreatorProducer() {
        return new CustomerCreatorProducerTestImpl();
    }

    @Override
    public DeliveryProducer deliveryProducer() {
        return new DeliveryProducerTestImpl();
    }

    @Override
    public CustomerProducer customerProducer() {
        return new CustomerProducerTestImpl();
    }
}
