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

public class OrderWithCityIdTestNG {

    private Order order;
    private long id;
    private short status;
    private int cityId;

    @BeforeClass
    @Parameters({"id", "status", "cityId"})
    public void setUpClass(long id, short status, int cityId) {
        this.id = id;
        this.status = status;
        this.cityId = cityId;

        order = new OrderWithCityIdImpl(new OrderSimpleImpl(id, status), cityId);
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("OrderWithCityId", "formForSend()");

        OrderPojo orderPojo = new OrderPojo(id);
        orderPojo.setStatus(status);
        orderPojo.setCityId(cityId);

        ObjectMapper objectMapper = TestUtils.objectMapperWithJavaTimeModule();
        assertEquals(
                objectMapper.writeValueAsString(order.formForSend()),
                objectMapper.writeValueAsString(orderPojo));

        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(order.formForSend()));

        testEnd("OrderWithCityId", "formForSend()");
    }
}
