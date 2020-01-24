/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.entity.OrderResponse;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.OrderResponseProducer;
import ru.fd.api.service.producer.repository.OrderRepositoryProducer;

import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class OrderRepositoryFailedIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired private OrderRepositoryProducer orderRepositoryProducer;
    @Autowired private OrderResponseProducer orderResponseProducer;

    private OrderRepository<Void, OrderResponse> orderRepo;

    @BeforeClass
    public void setUpClass() {
        orderRepo = orderRepositoryProducer.getOrderRepositoryFailedInstance(
                orderResponseProducer,
                new RepositoryException("Exception!"));

        testBegin("OrderRepositoryFailed");
    }

    @Test(priority = 1)
    public void read() throws RepositoryException, JsonProcessingException {
        testMethod("read()");

        OrderResponse orderResp = orderRepo.read(0);
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(orderResp.formForSend()));
    }

    @Test(priority = 2, expectedExceptions = RepositoryException.class, expectedExceptionsMessageRegExp = "Cannot insert in OrderRepositoryFailed instance")
    public void insert() throws RepositoryException {
        testMethod("insert()");

        orderRepo.insert();
    }

    @Test(priority = 3, expectedExceptions = RepositoryException.class, expectedExceptionsMessageRegExp = "Cannot readAll in OrderRepositoryFailed instance")
    public void readAll() throws RepositoryException {
        testMethod("readAll()");

        orderRepo.readAll();
    }

    @Test(priority = 4, expectedExceptions = RepositoryException.class, expectedExceptionsMessageRegExp = "Cannot readFirst in OrderRepositoryFailed instance")
    public void readFirst() throws RepositoryException {
        testMethod("readFirst()");

        orderRepo.readFirst(10);
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
//        writeTestTime(tr);
        testEnd("OrderRepositoryFailed");
    }
}
