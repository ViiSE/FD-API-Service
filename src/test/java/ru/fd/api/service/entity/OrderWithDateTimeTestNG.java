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
import ru.fd.api.service.data.OrderPojo;
import ru.fd.api.service.exception.CreatorException;
import test.util.TestUtils;

import java.time.LocalDateTime;

import static org.testng.Assert.assertEquals;
import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

public class OrderWithDateTimeTestNG {

    private Order order;
    private long id;
    private short status;
    private LocalDateTime dateTime;

    @BeforeClass
    @Parameters({"id", "status"})
    public void setUpClass(long id, short status) {
        this.id = id;
        this.status = status;
        this.dateTime = LocalDateTime.now();

        order = new OrderWithDateTimeImpl(new OrderSimpleImpl(id, status), dateTime);
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("OrderWithDateTime", "formForSend()");

        OrderPojo orderPojo = new OrderPojo(id);
        orderPojo.setStatus(status);
        orderPojo.setDateTime(dateTime);

        ObjectMapper objectMapper = TestUtils.objectMapperWithJavaTimeModule();
        assertEquals(
                objectMapper.writeValueAsString(order.formForSend()),
                objectMapper.writeValueAsString(orderPojo));

        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(order.formForSend()));

        testEnd("OrderWithDateTime", "formForSend()");
    }
}
