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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.OrdersService;
import ru.fd.api.service.creator.OrderCreator;
import ru.fd.api.service.creator.OrderCreatorFromBodyImpl;
import ru.fd.api.service.data.CustomerPojo;
import ru.fd.api.service.data.DeliveryPojo;
import ru.fd.api.service.data.OrderPojo;

import java.time.LocalDateTime;

import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class OrderCreatorProducerDefaultIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    private OrderCreatorProducer orderCreatorProducer;

    @Autowired
    private OrdersService ordersService;

    @Test
    public void getOrderCreatorFromBodyInstance() {
        testBegin("OrderCreatorProducer", "getOrderCreatorFromBodyInstance()");

        OrderPojo orderP = new OrderPojo(1L);
        orderP.setStatus((short) 0);
        orderP.setCityId(101);
        orderP.setCustomer(new CustomerPojo());
        orderP.setDelivery(new DeliveryPojo((short) 0, 101, "addr"));
        orderP.setDateTime(LocalDateTime.now());

        OrderCreator orderCr = orderCreatorProducer.getOrderCreatorFromBodyInstance(
                orderP,
                ordersService);

        assertTrue(orderCr instanceof OrderCreatorFromBodyImpl, "OrderCreator: not a valid type!");
        System.out.println("Instance: " + orderCr);

        testEnd("OrderCreatorProducer", "getOrderCreatorFromBodyInstance()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
