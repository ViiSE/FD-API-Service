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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.OrdersService;
import ru.fd.api.service.SQLQueryCreatorService;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.entity.OrderResponse;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.OrderResponseProducer;
import ru.fd.api.service.producer.repository.OrderRepositoryProducer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class OrderRepositoryWithoutCheckStatusIntegrationRealDBTestNG extends AbstractTestNGSpringContextTests {

    @Autowired private OrdersService ordersService;
    @Autowired private OrderRepositoryProducer orderRepositoryProducer;
    @Autowired private OrderResponseProducer orderResponseProducer;
    @Autowired private SQLQueryCreatorService sqlQueryCreatorService;

    private OrderRepository<OrderResponse, Void> orderRepo;

    @BeforeClass
    public void setUpClass() {
        Order order = ordersService.orderProducer().getOrderWithCommentInstance(
                ordersService.orderProducer().getOrderWithProductsInstance(
                        ordersService.orderProducer().getOrderSimpleInstance(
                                0,
                                "777",
                                ordersService.customerProducer().getCustomerFromCompanyImpl(
                                        ordersService.customerProducer().getCustomerWithNameInstance(
                                                ordersService.customerProducer().getCustomerWithEmailInstance(
                                                        ordersService.customerProducer().getCustomerWithPhoneNumberInstance(
                                                                ordersService.customerProducer().getCustomerSimpleInstance((short) 0),
                                                                "89098238724"),
                                                        "example@example.com"),
                                                "John Doe"),
                                        "22505",
                                        "43122"),
                                ordersService.deliveryProducer().getDeliveryWithDateInstance(
                                        ordersService.deliveryProducer().getDeliveryWithTimeIdInstance(
                                                ordersService.deliveryProducer().getDeliveryWithDepartmentIdInstance(
                                                        ordersService.deliveryProducer().getDeliverySimpleInstance((short) 0, "777", "ул. Ленинградская, 145Б"),
                                                        "100"),
                                                (short) 0),
                                        LocalDate.now()),
                                (short) 0,
                                LocalDateTime.now()),
                        ordersService.productsProducer().getOrderProductsDefaultInstance(
                                new ArrayList<>() {{
                                    add(ordersService.productProducer().getOrderProductSimpleInstance("id1", 10));
                                    add(ordersService.productProducer().getOrderProductSimpleInstance("id2", 20));
                                }})),
                "Коментарий");

        orderRepo = orderRepositoryProducer
                .getOrderRepositoryWithoutCheckStatusInstance(
                        order,
                        sqlQueryCreatorService.sqlQueryCreatorFromFileString(),
                        orderResponseProducer);
        testBegin("OrderRepositoryWithoutCheckStatus");
    }

    @Test(priority = 1)
    public void insert() throws RepositoryException, JsonProcessingException {
        testMethod("insert()");

        OrderResponse orderResp = orderRepo.insert();
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(orderResp.formForSend()));
    }

    @Test(priority = 2, expectedExceptions = RepositoryException.class, expectedExceptionsMessageRegExp = "Cannot read in OrderRepositoryWithoutCheckStatus instance")
    public void read() throws RepositoryException {
        testMethod("read()");

        orderRepo.read(0);
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
//        writeTestTime(tr);
    }

    @AfterClass
    public void shutdownClass() {
        testEnd("OrderRepositoryWithoutCheckStatus");
    }
}