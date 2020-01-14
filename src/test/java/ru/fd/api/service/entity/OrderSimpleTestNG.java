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
import ru.fd.api.service.data.CustomerPojo;
import ru.fd.api.service.data.DeliveryPojo;
import ru.fd.api.service.data.OrderPojo;
import ru.fd.api.service.exception.CreatorException;
import test.creator.CustomerCreatorTestImpl;
import test.creator.DeliveryCreatorTestImpl;

import java.time.LocalDateTime;

import static org.testng.Assert.assertEquals;
import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

public class OrderSimpleTestNG {

    private Order order;
    private long id;
    private String cityId;
    private Customer customer;
    private Delivery delivery;
    private short payTypeId;
    private LocalDateTime dateTime;

    @BeforeClass
    @Parameters({"id", "cityId", "payTypeId"})
    public void setUpClass(long id, String cityId, short payTypeId) throws CreatorException {
        this.id = id;
        this.cityId = cityId;
        this.customer = new CustomerCreatorTestImpl().create();
        this.delivery = new DeliveryCreatorTestImpl().create();
        this.payTypeId = payTypeId;
        this.dateTime = LocalDateTime.now();

        order = new OrderSimpleImpl(
                id,
                cityId,
                customer,
                delivery,
                payTypeId,
                dateTime);
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("OrderSimple", "formForSend()");

        OrderPojo orderPojo = new OrderPojo(
                id,
                cityId,
                (CustomerPojo) customer.formForSend(),
                (DeliveryPojo) delivery.formForSend(),
                payTypeId,
                dateTime);

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals(
                objectMapper.writeValueAsString(order.formForSend()),
                objectMapper.writeValueAsString(orderPojo));

        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(order.formForSend()));

        testEnd("OrderSimple", "formForSend()");
    }
}