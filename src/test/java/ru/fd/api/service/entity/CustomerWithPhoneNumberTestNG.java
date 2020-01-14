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
import ru.fd.api.service.data.CustomerPojo;
import test.util.TestUtils;

import static org.testng.Assert.*;
import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

public class CustomerWithPhoneNumberTestNG {

    private Customer customer;
    private short type;
    private String phoneNumber;

    @BeforeClass
    @Parameters({"type", "phoneNumber"})
    public void setUpClass(short type, String phoneNumber) {
        this.type = type;
        this.phoneNumber = phoneNumber;

        assertNotNull(phoneNumber, "PhoneNumber is null!");
        assertTrue(TestUtils.phoneNumberMatches(phoneNumber), "PhoneNumber is not valid!");

        customer = new CustomerWithPhoneNumberImpl(
                new CustomerSimpleImpl(type),
                phoneNumber);
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("CustomerWithPhoneNumber", "formForSend()");

        CustomerPojo customerPojo = new CustomerPojo();
        customerPojo.setType(type);
        customerPojo.setPhoneNumber(phoneNumber);

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals(
                objectMapper.writeValueAsString(customer.formForSend()),
                objectMapper.writeValueAsString(customerPojo));

        System.out.println(objectMapper.writeValueAsString(customer.formForSend()));

        testEnd("CustomerWithPhoneNumber", "formForSend()");
    }
}
