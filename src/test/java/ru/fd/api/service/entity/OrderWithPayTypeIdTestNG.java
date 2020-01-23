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

public class OrderWithPayTypeIdTestNG {

    private Order order;
    private long id;
    private short status;
    private short payTypeId;

    @BeforeClass
    @Parameters({"id", "status", "payTypeId"})
    public void setUpClass(long id, short status, short payTypeId){
        this.id = id;
        this.status = status;
        this.payTypeId = payTypeId;

        order = new OrderWithPayTypeIdImpl(new OrderSimpleImpl(id, status), payTypeId);
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("OrderWithPayTypeId", "formForSend()");

        OrderPojo orderPojo = new OrderPojo(id);
        orderPojo.setStatus(status);
        orderPojo.setPayTypeId(payTypeId);

        ObjectMapper objectMapper = TestUtils.objectMapperWithJavaTimeModule();
        assertEquals(
                objectMapper.writeValueAsString(order.formForSend()),
                objectMapper.writeValueAsString(orderPojo));

        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(order.formForSend()));

        testEnd("OrderWithPayTypeId", "formForSend()");
    }
}
