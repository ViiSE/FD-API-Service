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
import test.util.TestUtils;

import java.time.LocalDateTime;

import static org.testng.Assert.assertEquals;
import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

public class OrderWithCommentTestNG {

    private Order order;
    private long id;
    private String cityId;
    private Customer customer;
    private Delivery delivery;
    private short payTypeId;
    private String comment;
    private LocalDateTime dateTime;

    @BeforeClass
    @Parameters({"id", "cityId", "payTypeId", "comment"})
    public void setUpClass(long id, String cityId, short payTypeId, String comment) throws CreatorException {
        this.id = id;
        this.cityId = cityId;
        this.customer = new CustomerCreatorTestImpl().create();
        this.delivery = new DeliveryCreatorTestImpl().create();
        this.payTypeId = payTypeId;
        this.comment = comment;
        this.dateTime = LocalDateTime.now();

        order = new OrderWithCommentImpl(
                new OrderSimpleImpl(
                        id,
                        cityId,
                        customer,
                        delivery,
                        payTypeId,
                        dateTime),
                comment);
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("OrderWithComment", "formForSend()");

        OrderPojo orderPojo = new OrderPojo(
                id,
                cityId,
                (CustomerPojo) customer.formForSend(),
                (DeliveryPojo) delivery.formForSend(),
                payTypeId,
                dateTime);
        orderPojo.setComment(comment);

        ObjectMapper objectMapper = TestUtils.objectMapperWithJavaTimeModule();
        assertEquals(
                objectMapper.writeValueAsString(order.formForSend()),
                objectMapper.writeValueAsString(orderPojo));

        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(order.formForSend()));

        testEnd("OrderWithComment", "formForSend()");
    }
}
