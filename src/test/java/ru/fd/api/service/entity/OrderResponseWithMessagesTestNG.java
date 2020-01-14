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

import static org.testng.Assert.*;
import static test.message.TestMessage.*;

public class OrderResponseWithMessagesTestNG {

    private OrderResponse orderResponse;

    private short status;
    private long id;
    private String message;

    @BeforeClass
    @Parameters({"id", "status", "message"})
    public void setUpClass(long id, short status, String message) {
        assertFalse(id < 0, "Id is less than 0!");
        assertNotNull(message, "Message is null!!");

        this.id = id;
        this.status = status;
        this.message = message;
        orderResponse = new OrderResponseWithMessageImpl(
                new OrderResponseSimpleImpl(id, status),
                message);

        testBegin("OrderResponseWithMessagesTestNG");
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
        orderResponsePojo.setMessage(message);

        assertEquals(
                objectMapper.writeValueAsString(orderResponse.formForSend()),
                objectMapper.writeValueAsString(orderResponsePojo),
                "OrderResponsePojo is not equals!");

        System.out.println(objectMapper.writeValueAsString(orderResponse.formForSend()));
    }

    @AfterClass
    public void teardownClass() {
        testEnd("OrderResponseWithMessagesTestNG");
    }
}
