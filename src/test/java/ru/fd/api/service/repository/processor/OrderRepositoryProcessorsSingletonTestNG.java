/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.repository.processor;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.constant.Processors;
import ru.fd.api.service.producer.repository.OrderRepositoryProducer;
import ru.fd.api.service.producer.repository.processor.OrderRepositoryProcessorProducer;
import ru.fd.api.service.producer.repository.processor.OrderRepositoryProcessorProducerDefaultImpl;
import test.producer.repository.OrderRepositoryProcessorProducerTestImpl;
import test.producer.repository.OrderRepositoryProducerTestImpl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

public class OrderRepositoryProcessorsSingletonTestNG {

    private OrderRepositoryProcessors orderRepoProcessors;

    @BeforeClass
    public void setUpClass() {
        OrderRepositoryProcessorProducer orderRepoProd = new OrderRepositoryProcessorProducerTestImpl();
        orderRepoProcessors = new OrderRepositoryProcessorsSingletonImpl(orderRepoProd);
        testBegin("OrderRepositoryProcessorsSingleton");
    }

    @Test
    public void processor_createOrderDeprecated() {
        testMethod("processor() ['create order __DEPRECATED__']");

        OrderRepositoryProcessor processor = orderRepoProcessors.processor(Processors.CREATE_ORDER_DEPRECATED);
        assertNotNull(processor, "Simple processor is null!");
        assertTrue(processor instanceof CreateOrderRepositoryDeprecatedProcessorImpl, "Processor is not instanceof create order __DEPRECATED__!");
        System.out.println("Simple processor: " + processor);
    }

    @Test
    public void processor_createOrder() {
        testMethod("processor() ['create order']");

        OrderRepositoryProcessor processor = orderRepoProcessors.processor(Processors.CREATE_ORDER);
        assertNotNull(processor, "Simple processor is null!");
        assertTrue(processor instanceof CreateOrderRepositoryProcessorImpl, "Processor is not instanceof create order!");
        System.out.println("Simple processor: " + processor);
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }

    @AfterClass
    public void teardownClass() {
        testEnd("OrderRepositoryProcessorsSingleton");
    }
}
