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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.fd.api.service.entity.*;
import test.producer.entity.OrderResponseProducerTestImpl;
import test.producer.entity.ProductProducerTestImpl;
import test.producer.entity.ProductsProducerTestImpl;
import test.producer.repository.OrderRepositoryProducerTestImpl;

import java.util.ArrayList;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

public class CreateOrderRepositoryProcessorTestNG {

    private OrderRepositoryProcessor processor;
    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeTest
    public void setUpClass() {
        processor = new CreateOrderRepositoryDeprecatedProcessorImpl(
                new OrderRepositoryProducerTestImpl(),
                null,
                new ProductProducerTestImpl(),
                new ProductsProducerTestImpl(),
                new OrderResponseProducerTestImpl());
    }

    @Test
    public void apply() throws JsonProcessingException {
        testBegin("CreateOrderRepositoryProcessor", "apply()");

        OrderResponse oResp = (OrderResponse) processor.apply(new OrderWithProductsImpl(
                new OrderSimpleImpl(0, (short) 0),
                new OrderProductsDefaultImpl(new ArrayList<>() {{
                    add(new OrderProductSimpleImpl("id1", 10));
                    add(new OrderProductSimpleImpl("id2", 20));
                }})));
        assertNotNull(oResp, "OrderResponse is null!");
        assertTrue(oResp instanceof OrderResponseWithProductsImpl, "Order repository is not instanceof with products!");
        System.out.println("OrderResponse: " + oResp);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(oResp.formForSend()));

        testEnd("CreateOrderRepositoryProcessor", "apply()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
