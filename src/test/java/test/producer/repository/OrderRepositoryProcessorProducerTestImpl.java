/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package test.producer.repository;

import ru.fd.api.service.producer.repository.processor.OrderRepositoryProcessorProducer;
import ru.fd.api.service.repository.processor.CreateOrderRepositoryDeprecatedProcessorImpl;
import ru.fd.api.service.repository.processor.CreateOrderRepositoryProcessorImpl;
import ru.fd.api.service.repository.processor.OrderRepositoryProcessor;

public class OrderRepositoryProcessorProducerTestImpl implements OrderRepositoryProcessorProducer {

    @Override
    public OrderRepositoryProcessor getCreateOrderRepositoryDeprecatedProcessorInstance() {
        return new CreateOrderRepositoryDeprecatedProcessorImpl(
                null,
                null,
                null,
                null,
                null);
    }

    @Override
    public OrderRepositoryProcessor getCreateOrderRepositoryProcessorInstance() {
        return new CreateOrderRepositoryProcessorImpl(
                null,
                null,
                null);
    }
}
