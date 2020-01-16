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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.DeliveryPojo;
import test.util.TestUtils;

import static org.testng.Assert.assertEquals;
import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

public class DeliverySimpleTestNG {

    private Delivery delivery;
    private short type;
    private String city;
    private String address;

    @BeforeClass
    @Parameters({"type", "cityId", "address"})
    public void setUpClass(short type, String cityId, String address) {
        this.type = type;
        this.city = cityId;
        this.address = address;
        delivery = new DeliverySimpleImpl(
                type,
                cityId,
                address);
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("DeliverySimple", "formForSend()");

        DeliveryPojo deliveryPojo = new DeliveryPojo(type, city, address);

        ObjectMapper objectMapper = TestUtils.objectMapperWithJavaTimeModule();
        assertEquals(
                objectMapper.writeValueAsString(delivery.formForSend()),
                objectMapper.writeValueAsString(deliveryPojo));

        System.out.println(objectMapper.writeValueAsString(delivery.formForSend()));

        testEnd("DeliverySimple", "formForSend()");
    }
}
