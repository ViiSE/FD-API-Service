/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.producer.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.*;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.entity.*;

import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class CustomerProducerIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    private CustomerProducer customerProducer;

    private Customer customerSimple;

    @BeforeClass
    public void setUpClass() {
        testBegin("CustomerInstance");
    }

    @Test(priority = 1)
    @Parameters({"type"})
    public void getCustomerSimpleInstance(short type) {
        testMethod("getCustomerSimpleInstance()");

        customerSimple = customerProducer.getCustomerSimpleInstance(type);

        assertTrue(customerSimple instanceof CustomerSimpleImpl, "Customer: not a valid type!");
        System.out.println("Instance: " + customerSimple);
    }

    @Test(priority = 2)
    @Parameters({"phoneNumber"})
    public void getCustomerWithPhoneNumberInstance(String phoneNumber) {
        testMethod("getCustomerWithPhoneNumberInstance()");

        Customer customer = customerProducer.getCustomerWithPhoneNumberInstance(customerSimple, phoneNumber);

        assertTrue(customer instanceof CustomerWithPhoneNumberImpl, "Customer: not a valid type!");
        System.out.println("Instance: " + customer);
    }

    @Test(priority = 3)
    @Parameters({"email"})
    public void getCustomerWithEmailInstance(String email) {
        testMethod("getCustomerWithEmailInstance()");

        Customer customer = customerProducer.getCustomerWithEmailInstance(customerSimple, email);

        assertTrue(customer instanceof CustomerWithEmailImpl, "Customer: not a valid type!");
        System.out.println("Instance: " + customer);
    }

    @Test(priority = 4)
    @Parameters({"name"})
    public void getCustomerWithNameInstance(String name) {
        testMethod("getCustomerWithNameInstance()");

        Customer customer = customerProducer.getCustomerWithNameInstance(customerSimple, name);

        assertTrue(customer instanceof CustomerWithNameImpl, "Customer: not a valid type!");
        System.out.println("Instance: " + customer);
    }

    @Test(priority = 5)
    @Parameters({"inn", "kpp"})
    public void getCustomerFromCompanyInstance(String inn, String kpp) {
        testMethod("getCustomerFromCompanyInstance()");

        Customer customer = customerProducer.getCustomerFromCompanyInstance(customerSimple, inn, kpp);

        assertTrue(customer instanceof CustomerFromCompanyImpl, "Customer: not a valid type!");
        System.out.println("Instance: " + customer);
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }

    @AfterClass
    public void shutdownClass() {
        testEnd("CustomerInstance");
    }
}
