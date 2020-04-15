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
import test.util.TestUtils;

import static org.testng.Assert.assertEquals;
import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

public class OrderWithCommentTestNG {

    private Order order;
    private long id;
    private short status;
    private String comment;

    @BeforeClass
    @Parameters({"id", "status", "comment"})
    public void setUpClass(long id, short status, String comment) {
        this.id = id;
        this.status = status;
        this.comment = comment;

        order = new OrderWithCommentImpl(new OrderSimpleImpl(id, status), comment);
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("OrderWithComment", "formForSend()");

        OrderPojo orderPojo = new OrderPojo(id);
        orderPojo.setStatus(status);
        orderPojo.setComment(comment);

        ObjectMapper objectMapper = TestUtils.objectMapperWithJavaTimeModule();
        assertEquals(
                objectMapper.writeValueAsString(order.formForSend()),
                objectMapper.writeValueAsString(orderPojo));

        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(order.formForSend()));

        testEnd("OrderWithComment", "formForSend()");
    }
}
