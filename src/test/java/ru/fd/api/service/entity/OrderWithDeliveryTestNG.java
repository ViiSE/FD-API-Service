/*
 *  Copyright 2020 FD Company. All rights reserved.
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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.DeliveryPojo;
import ru.fd.api.service.data.OrderPojo;
import test.util.TestUtils;

import java.time.LocalDate;

import static org.testng.Assert.assertEquals;
import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

public class OrderWithDeliveryTestNG {

    private Order order;
    private long id;
    private short status;
    private Delivery delivery;

    @BeforeClass
    @Parameters({"id", "status"})
    public void setUpClass(long id, short status) {
        this.id = id;
        this.status = status;
        this.delivery = new DeliveryWithDateImpl(
                new DeliveryWithTimeIdImpl(
                        new DeliveryWithDepartmentIdImpl(
                                new DeliverySimpleImpl(
                                        (short) 0,
                                        1,
                                        "st. Example, 404"),
                                "dep_1"),
                        (short) 0),
                LocalDate.now());

        order = new OrderWithDeliveryImpl(new OrderSimpleImpl(id, status), delivery);
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("OrderWithDelivery", "formForSend()");

        OrderPojo orderPojo = new OrderPojo(id);
        orderPojo.setStatus(status);
        orderPojo.setDelivery((DeliveryPojo) delivery.formForSend());

        ObjectMapper objectMapper = TestUtils.objectMapperWithJavaTimeModule();
        assertEquals(
                objectMapper.writeValueAsString(order.formForSend()),
                objectMapper.writeValueAsString(orderPojo));

        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(order.formForSend()));

        testEnd("OrderWithDelivery", "formForSend()");
    }
}
