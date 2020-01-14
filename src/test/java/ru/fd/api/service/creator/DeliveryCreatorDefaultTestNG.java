/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.creator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.*;
import ru.fd.api.service.data.DeliveryPojo;
import ru.fd.api.service.entity.Delivery;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.producer.entity.DeliveryProducer;
import test.producer.entity.DeliveryProducerTestImpl;

import java.time.LocalDate;

import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

public class DeliveryCreatorDefaultTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private DeliveryProducer deliveryProducer;

    @BeforeClass
    public void setUpClass() {
        deliveryProducer = new DeliveryProducerTestImpl();

        testBegin("DeliveryCreatorDefault");
    }

    @Test
    @Parameters({"type", "cityId", "address", "departmentId", "deliveryTimeId"})
    public void create(short type, String cityId, String address, String departmentId, short deliveryTimeId) throws CreatorException, JsonProcessingException {
        testMethod("create()");

        DeliveryPojo deliveryPojo = new DeliveryPojo(type, cityId, address);
        deliveryPojo.setDepartmentId(departmentId);
        deliveryPojo.setDeliveryDate(LocalDate.now());
        deliveryPojo.setDeliveryTimeId(deliveryTimeId);

        Delivery delivery = new DeliveryCreatorDefaultImpl(deliveryPojo, deliveryProducer).create();

        assertTrue(mapper.writeValueAsString(delivery.formForSend()).contains("\"type\""));
        assertTrue(mapper.writeValueAsString(delivery.formForSend()).contains("\"city_id\""));
        assertTrue(mapper.writeValueAsString(delivery.formForSend()).contains("\"address\""));
        assertTrue(mapper.writeValueAsString(delivery.formForSend()).contains("\"department_id\""));
        assertTrue(mapper.writeValueAsString(delivery.formForSend()).contains("\"delivery_time_id\""));
        assertTrue(mapper.writeValueAsString(delivery.formForSend()).contains("\"delivery_date\""));
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(delivery.formForSend()));
    }

    @Test(expectedExceptions = CreatorException.class, expectedExceptionsMessageRegExp = "Delivery: city id required")
    @Parameters({"type", "address", "departmentId", "deliveryTimeId"})
    public void create_withoutCityId(short type, String address, String departmentId, short deliveryTimeId) throws CreatorException, JsonProcessingException {
        testMethod("create() [without cityId]");

        DeliveryPojo deliveryPojo = new DeliveryPojo(type, "", address);
        deliveryPojo.setDepartmentId(departmentId);
        deliveryPojo.setDeliveryDate(LocalDate.now());
        deliveryPojo.setDeliveryTimeId(deliveryTimeId);

        new DeliveryCreatorDefaultImpl(deliveryPojo, deliveryProducer).create();
    }

    @Test(expectedExceptions = CreatorException.class, expectedExceptionsMessageRegExp = "Delivery: address required")
    @Parameters({"type", "cityId", "departmentId", "deliveryTimeId"})
    public void create_withoutAddress(short type, String cityId, String departmentId, short deliveryTimeId) throws CreatorException, JsonProcessingException {
        testMethod("create() [without address]");

        DeliveryPojo deliveryPojo = new DeliveryPojo(type, cityId, "");
        deliveryPojo.setDepartmentId(departmentId);
        deliveryPojo.setDeliveryDate(LocalDate.now());
        deliveryPojo.setDeliveryTimeId(deliveryTimeId);

        new DeliveryCreatorDefaultImpl(deliveryPojo, deliveryProducer).create();
    }

    @Test(expectedExceptions = CreatorException.class, expectedExceptionsMessageRegExp = "Delivery: department id required")
    @Parameters({"type", "cityId", "departmentId", "deliveryTimeId"})
    public void create_withoutDepartmentId(short type, String cityId, String address, short deliveryTimeId) throws CreatorException, JsonProcessingException {
        testMethod("create() [without departmentId]");

        DeliveryPojo deliveryPojo = new DeliveryPojo(type, cityId, address);
        deliveryPojo.setDeliveryDate(LocalDate.now());
        deliveryPojo.setDeliveryTimeId(deliveryTimeId);

        new DeliveryCreatorDefaultImpl(deliveryPojo, deliveryProducer).create();
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }

    @AfterClass
    public void teardownClass() {
        testEnd("DeliveryCreatorDefault");
    }
}
