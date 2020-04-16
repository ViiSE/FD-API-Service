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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.OrderResponsePojo;

import java.util.ArrayList;

import static org.testng.Assert.*;
import static test.message.TestMessage.*;

public class OrderResponseWithProductsTestNG {

    private OrderResponse orderResponse;

    private short status;
    private long id;

//    private String opId1;
//    private String opId2;
//    private int opq1;
//    private int opq2;

    @BeforeClass
    @Parameters({"id", "status", "opId1", "opId2", "opq1", "opq2"})
    public void setUpClass(long id, short status, String opId1, String opId2, int opq1, int opq2) {
        assertFalse(id < 0, "Id is less than 0!");

        assertNotNull(opId1, "OrderProduct Id is null!");
        assertNotNull(opId2, "OrderProduct Id is null!");

        assertFalse(opq1 <= 0, "OrderProduct quantity is less or equals than 0!");
        assertFalse(opq2 <= 0, "OrderProduct quantity is less or equals than 0!");

        Products orderProducts = new OrderProductsImpl(new ArrayList<>() {{
            add(new OrderProductSimpleImpl(opId1, opq1, 10.99f));
            add(new OrderProductSimpleImpl(opId2, opq2, 10.99f));
        }});

        this.id = id;
        this.status = status;

//        this.opId1 = opId1;
//        this.opId2 = opId2;

//        this.opq1 = opq1;
//        this.opq2 = opq2;

        orderResponse = new OrderResponseWithProductsImpl(
                new OrderResponseSimpleImpl(id, status),
                orderProducts);

        testBegin("OrderResponseWithProductsTestNG");
    }

    @Test
    public void status() {
        testMethod("status()");

        assertEquals(orderResponse.status(), status, "Statuses is not equals!");
    }

    @Test
    public void id() {
        testMethod("id()");

        assertEquals(orderResponse.id(), id, "Id's is not equals!");
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testMethod("formForSend()");

        ObjectMapper objectMapper = new ObjectMapper();

        OrderResponsePojo orderResponsePojo = new OrderResponsePojo();
        orderResponsePojo.setId(id);
        orderResponsePojo.setStatus(status);
//        orderResponsePojo.setProductsOrderPojo(
//                new ProductsOrderPojo(new ArrayList<>() {{
//                    add(new ProductOrderPojo(opId1, opq1));
//                    add(new ProductOrderPojo(opId2, opq2));
//                }}));

        assertEquals(
                objectMapper.writeValueAsString(orderResponse.formForSend()),
                objectMapper.writeValueAsString(orderResponsePojo),
                "OrderResponsePojo is not equals!");

        System.out.println(objectMapper.writeValueAsString(orderResponse.formForSend()));
    }

    @AfterClass
    public void teardownClass() {
        testEnd("OrderResponseWithProductsTestNG");
    }
}
