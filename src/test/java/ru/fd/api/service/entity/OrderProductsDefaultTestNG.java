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
import ru.fd.api.service.data.ProductsOrderPojo;

import java.util.ArrayList;

import static org.testng.Assert.*;
import static test.message.TestMessage.*;

public class OrderProductsDefaultTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Products orderProducts;

    private String id1;
    private int quantity1;

    private String id2;
    private int quantity2;

    @BeforeClass
    @Parameters({"id1", "quantity1", "id2", "quantity2"})
    public void setUpClass(String id1, int quantity1, String id2, int quantity2) {
        assertNotNull(id1, "ID cannot be null!");
        assertFalse(id1.isEmpty(), "ID is empty!");
        assertNotNull(id2, "ID cannot be null!");
        assertFalse(id2.isEmpty(), "ID is empty!");

        assertFalse(quantity1 <= 0, "Quantity is less or equals than 0!");
        assertFalse(quantity2 <= 0, "Quantity is less or equals than 0!");

        orderProducts = new OrderProductsImpl(new ArrayList<>() {{
            add(new OrderProductSimpleImpl(id1, quantity1));
            add(new OrderProductSimpleImpl(id2, quantity2));
        }});

        this.id1 = id1;
        this.quantity1 = quantity1;

        this.id2 = id2;
        this.quantity2 = quantity2;

        assertNotNull(orderProducts, "Product is null!");

        testBegin("OrderProductsDefault");
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testMethod("formForSend()");

        ProductsOrderPojo productsOrderPojo = (ProductsOrderPojo) orderProducts.formForSend();
        assertNotNull(productsOrderPojo, "ProductsPojo is null!");
        assertEquals(productsOrderPojo.getProducts().get(0).getId(), id1);
        assertEquals(productsOrderPojo.getProducts().get(1).getId(), id2);
        assertEquals(productsOrderPojo.getProducts().get(0).getQuantity(), quantity1);
        assertEquals(productsOrderPojo.getProducts().get(1).getQuantity(), quantity2);
        System.out.println(mapper.writeValueAsString(productsOrderPojo));
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }

    @AfterClass
    public void teardownClass() {
        testEnd("OrderProductsDefault");
    }
}
