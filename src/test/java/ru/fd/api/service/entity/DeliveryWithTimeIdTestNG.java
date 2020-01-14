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

import static org.testng.Assert.assertEquals;
import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

public class DeliveryWithTimeIdTestNG {

    private Delivery delivery;
    private short type;
    private String city;
    private String address;
    private short timeId;

    @BeforeClass
    @Parameters({"type", "cityId", "address", "timeId"})
    public void setUpClass(short type, String cityId, String address, short timeId) {
        this.type = type;
        this.city = cityId;
        this.address = address;
        this.timeId = timeId;
        delivery = new DeliveryWithTimeIdImpl(
                new DeliverySimpleImpl(
                        type,
                        cityId,
                        address),
                timeId);
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("DeliveryWithTimeId", "formForSend()");

        DeliveryPojo deliveryPojo = new DeliveryPojo(type, city, address);
        deliveryPojo.setDeliveryTimeId(timeId);

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals(
                objectMapper.writeValueAsString(delivery.formForSend()),
                objectMapper.writeValueAsString(deliveryPojo));

        System.out.println(objectMapper.writeValueAsString(delivery.formForSend()));

        testEnd("DeliveryWithTimeId", "formForSend()");
    }
}
