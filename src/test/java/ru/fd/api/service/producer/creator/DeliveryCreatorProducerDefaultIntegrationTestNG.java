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
import ru.fd.api.service.creator.DeliveryCreator;
import ru.fd.api.service.creator.DeliveryCreatorDefaultImpl;
import ru.fd.api.service.data.DeliveryPojo;
import ru.fd.api.service.producer.entity.DeliveryProducer;

import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class DeliveryCreatorProducerDefaultIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    private DeliveryCreatorProducer deliveryCreatorProducer;

    @Autowired
    private DeliveryProducer deliveryProducer;

    @Test
    public void getDeliveryCreatorDefaultInstance() {
        testBegin("DeliveryCreatorProducer", "getDeliveryCreatorDefaultInstance()");

        DeliveryCreator delCr = deliveryCreatorProducer
                .getDeliveryCreatorDefaultInstance(
                        new DeliveryPojo((short) 0, "cId1", "addr"),
                        deliveryProducer);

        assertTrue(delCr instanceof DeliveryCreatorDefaultImpl, "DeliveryCreator: not a valid type!");
        System.out.println("Instance: " + delCr);

        testEnd("DeliveryCreatorProducer", "getDeliveryCreatorDefaultInstance()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
