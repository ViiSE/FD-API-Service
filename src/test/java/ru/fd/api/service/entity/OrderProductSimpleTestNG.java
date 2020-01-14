/*
 *  Copyright 2019 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.*;
import ru.fd.api.service.data.ProductOrderPojo;

import static org.testng.Assert.*;
import static test.message.TestMessage.*;

public class OrderProductSimpleTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Product product;

    private String id;
    private int quantity;

    @BeforeClass
    @Parameters({"id", "quantity"})
    public void setUpClass(String id, int quantity) {
        assertNotNull(id, "ID cannot be null!");
        assertFalse(id.isEmpty(), "ID is empty!");

        assertFalse(quantity <= 0, "Quantity is less or equals than 0!");

        this.id = id;
        this.quantity = quantity;

        product = new OrderProductSimpleImpl(id, quantity);
        assertNotNull(product, "Product is null!");

        testBegin("OrderProductSimple");
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testMethod("formForSend()");

        ProductOrderPojo orderProductPojo = (ProductOrderPojo) product.formForSend();
        assertNotNull(orderProductPojo, "ProductPojo is null!");
        assertEquals(orderProductPojo.getId(), id);
        assertEquals(orderProductPojo.getQuantity(), quantity);
        System.out.println(mapper.writeValueAsString(orderProductPojo));
    }

    @Test
    public void id() {
        testMethod("id()");

        assertEquals(product.id(), id);
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }

    @AfterClass
    public void teardownClass() {
        testEnd("OrderProductSimple");
    }
}
