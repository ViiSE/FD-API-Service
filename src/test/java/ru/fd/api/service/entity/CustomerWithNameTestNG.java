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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

public class CustomerWithNameTestNG {

    private Customer customer;
    private short type;
    private String name;

    @BeforeClass
    @Parameters({"type", "name"})
    public void setUpClass(short type, String name) {
        this.type = type;
        this.name = name;

        assertNotNull(name, "Name is null!");

        customer = new CustomerWithNameImpl(
                new CustomerSimpleImpl(type),
                name);
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("CustomerWithName", "formForSend()");

        CustomerPojo customerPojo = new CustomerPojo();
        customerPojo.setType(type);
        customerPojo.setName(name);

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals(
                objectMapper.writeValueAsString(customer.formForSend()),
                objectMapper.writeValueAsString(customerPojo));

        System.out.println(objectMapper.writeValueAsString(customer.formForSend()));

        testEnd("CustomerWithName", "formForSend()");
    }
}
