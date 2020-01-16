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
import test.util.TestUtils;

import java.time.LocalDate;

import static org.testng.Assert.assertEquals;
import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

public class DeliveryWithDateTestNG {

    private Delivery delivery;
    private short type;
    private String city;
    private String address;
    private LocalDate date;

    @BeforeClass
    @Parameters({"type", "cityId", "address"})
    public void setUpClass(short type, String cityId, String address) {
        this.type = type;
        this.city = cityId;
        this.address = address;
        this.date = LocalDate.now();
        delivery = new DeliveryWithDateImpl(
                new DeliverySimpleImpl(
                        type,
                        cityId,
                        address),
                date);
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("DeliveryWithDate", "formForSend()");

        DeliveryPojo deliveryPojo = new DeliveryPojo(type, city, address);
        deliveryPojo.setDeliveryDate(date);

        ObjectMapper objectMapper = TestUtils.objectMapperWithJavaTimeModule();
        assertEquals(
                objectMapper.writeValueAsString(delivery.formForSend()),
                objectMapper.writeValueAsString(deliveryPojo));

        System.out.println(objectMapper.writeValueAsString(delivery.formForSend()));

        testEnd("DeliveryWithDate", "formForSend()");
    }
}
