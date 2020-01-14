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
import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

public class CustomerFromCompanyTestNG {

    private Customer customer;
    private short type;
    private String inn;
    private String kpp;

    @BeforeClass
    @Parameters({"type", "inn", "kpp"})
    public void setUpClass(short type, String inn, String kpp) {
        this.type = type;
        this.inn = inn;
        this.kpp = kpp;
        customer = new CustomerFromCompanyImpl(
                new CustomerSimpleImpl(type),
                inn,
                kpp);
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("CustomerFromCompany", "formForSend()");

        CustomerPojo customerPojo = new CustomerPojo();
        customerPojo.setType(type);
        customerPojo.setInn(inn);
        customerPojo.setKpp(kpp);

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals(
                objectMapper.writeValueAsString(customer.formForSend()),
                objectMapper.writeValueAsString(customerPojo));

        System.out.println(objectMapper.writeValueAsString(customer.formForSend()));

        testEnd("CustomerFromCompany", "formForSend()");
    }
}
