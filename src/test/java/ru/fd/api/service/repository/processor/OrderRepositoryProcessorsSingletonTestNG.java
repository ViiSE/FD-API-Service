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
import test.producer.repository.OrderRepositoryProducerTestImpl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

public class OrderRepositoryProcessorsSingletonTestNG {

    private OrderRepositoryProcessors orderRepoProcessors;

    @BeforeClass
    public void setUpClass() {
        OrderRepositoryProducer orderRepoProd = new OrderRepositoryProducerTestImpl();
        orderRepoProcessors = new OrderRepositoryProcessorsSingletonImpl(
                orderRepoProd,
                null,
                null,
                null,
                null);
        testBegin("OrderRepositoryProcessorsSingleton");
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
