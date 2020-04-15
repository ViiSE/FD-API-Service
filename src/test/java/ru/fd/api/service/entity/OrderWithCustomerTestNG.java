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
import ru.fd.api.service.data.CustomerPojo;
import ru.fd.api.service.data.OrderPojo;
import test.util.TestUtils;

import static org.testng.Assert.assertEquals;
import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

public class OrderWithCustomerTestNG {

    private Order order;
    private long id;
    private short status;
    private Customer customer;

    @BeforeClass
    @Parameters({"id", "status"})
    public void setUpClass(long id, short status) {
        this.id = id;
        this.status = status;
        this.customer = new CustomerFromCompanyImpl(
                new CustomerWithPhoneNumberImpl(
                        new CustomerWithEmailImpl(
                                new CustomerWithNameImpl(
                                        new CustomerSimpleImpl((short) 0),
                                        "John Doe"),
                                "example@example.com"),
                        "85551111111"),
                "2323313",
                "4676546");

        order = new OrderWithCustomerImpl(new OrderSimpleImpl(id, status), customer);
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("OrderWithCustomer", "formForSend()");

        OrderPojo orderPojo = new OrderPojo(id);
        orderPojo.setStatus(status);
        orderPojo.setCustomer((CustomerPojo) customer.formForSend());

        ObjectMapper objectMapper = TestUtils.objectMapperWithJavaTimeModule();
        assertEquals(
                objectMapper.writeValueAsString(order.formForSend()),
                objectMapper.writeValueAsString(orderPojo));

        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(order.formForSend()));

        testEnd("OrderWithCustomer", "formForSend()");
    }
}
