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

import static org.testng.Assert.assertEquals;
import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

public class DeliveryWithDepartmentIdTestNG {

    private Delivery delivery;
    private short type;
    private int cityId;
    private String address;
    private String departmentId;

    @BeforeClass
    @Parameters({"type", "cityId", "address", "departmentId"})
    public void setUpClass(short type, int cityId, String address, String departmentId) {
        this.type = type;
        this.cityId = cityId;
        this.address = address;
        this.departmentId = departmentId;
        delivery = new DeliveryWithDepartmentIdImpl(
                new DeliverySimpleImpl(
                        type,
                        cityId,
                        address),
                departmentId);
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("DeliveryWithDepartmentId", "formForSend()");

        DeliveryPojo deliveryPojo = new DeliveryPojo(type, cityId, address);
        deliveryPojo.setDepartmentId(departmentId);

        ObjectMapper objectMapper = TestUtils.objectMapperWithJavaTimeModule();
        assertEquals(
                objectMapper.writeValueAsString(delivery.formForSend()),
                objectMapper.writeValueAsString(deliveryPojo));

        System.out.println(objectMapper.writeValueAsString(delivery.formForSend()));

        testEnd("DeliveryWithDepartmentId", "formForSend()");
    }
}
