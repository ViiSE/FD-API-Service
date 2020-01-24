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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.creator.CustomerCreator;
import ru.fd.api.service.creator.CustomerCreatorEmailOrPhoneRequiredImpl;
import ru.fd.api.service.data.CustomerPojo;
import ru.fd.api.service.producer.entity.CustomerProducer;

import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class CustomerCreatorProducerDefaultIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("customerCreatorProducerDefault")
    private CustomerCreatorProducer customerCreatorProducer;

    @Autowired
    private CustomerProducer customerProducer;

    @Test
    public void getCustomerCreatorEmailOrPhoneRequiredInstance() {
        testBegin("CustomerCreatorProducerDefault", "getCustomerCreatorEmailOrPhoneRequiredInstance()");

        CustomerCreator custCr = customerCreatorProducer
                .getCustomerCreatorEmailOrPhoneRequiredInstance(
                        new CustomerPojo(),
                        customerProducer);

        assertTrue(custCr instanceof CustomerCreatorEmailOrPhoneRequiredImpl, "CustomerCreator: not a valid type!");
        System.out.println("Instance: " + custCr);

        testEnd("CustomerCreatorProducerDefault", "getCustomerCreatorEmailOrPhoneRequiredInstance()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
