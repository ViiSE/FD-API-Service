/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.creator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.data.OrderPojo;
import ru.fd.api.service.data.ProductOrderPojo;
import ru.fd.api.service.data.ProductsOrderPojo;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.entity.ProductsProducer;
import test.producer.entity.ProductProducerTestImpl;
import test.producer.entity.ProductsProducerTestImpl;

import java.util.ArrayList;

import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

public class OrderProductsCreatorDefaultTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private ProductProducer productProducer;
    private ProductsProducer productsProducer;
    private OrderPojo order;

    @BeforeClass
    public void setUpClass() {
        productsProducer = new ProductsProducerTestImpl();
        productProducer = new ProductProducerTestImpl();
        order = createOrder();

        testBegin("OrderProductsCreatorDefault");
    }

    @Test
    public void create() throws CreatorException, JsonProcessingException {
        testMethod("create()");

        Products orderProducts = new OrderProductsCreatorDefaultImpl(
                order,
                productsProducer,
                productProducer).create();

        assertTrue(mapper.writeValueAsString(orderProducts.formForSend()).contains("\"id\""));
        assertTrue(mapper.writeValueAsString(orderProducts.formForSend()).contains("\"quantity\""));
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orderProducts.formForSend()));
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }

    @AfterClass
    public void teardownClass() {
        testEnd("OrderProductsCreatorDefault");
    }

    private OrderPojo createOrder() {
       OrderPojo orderPojo = new OrderPojo(1L);
       orderPojo.setProducts(
               new ProductsOrderPojo(
                       new ArrayList<>() {{
                           add(new ProductOrderPojo("item1", 10));
                           add(new ProductOrderPojo("item2", 20));
                       }})
                       .getProducts());

       return orderPojo;
    }
}
