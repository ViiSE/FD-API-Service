/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.data.OrderPojo;
import ru.fd.api.service.data.OrderResponsePojo;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.entity.OrderResponse;
import ru.fd.api.service.filter.APIFilter;
import ru.fd.api.service.process.Process;
import ru.fd.api.service.producer.entity.*;
import test.util.TestUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.assertEquals;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class OrdersControllerIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired private WebApplicationContext context;
    @Autowired private APIFilter filter;

    @Autowired private ObjectMapper objectMapper;
    @Autowired private OrderProducer oPr;
    @Autowired private DeliveryProducer dPr;
    @Autowired private CustomerProducer cPr;
    @Autowired private ProductProducer pPr;
    @Autowired private ProductsProducer psPr;

    @Autowired
    @Qualifier("psChainCreateOrder")
    private Process<OrderResponse, OrderPojo> ordersService;

    @Value("${fd.api.service.jwt-id}")      private String id;
    @Value("${fd.api.service.jwt-issuer}")  private String issuer;
    @Value("${fd.api.service.jwt-secret}")  private String secret;
    @Value("${fd.api.service.jwt-subject}") private String subject;

    private MockMvc mockMvc;
    private String testToken;

    private Order order;

    @BeforeClass
    @Parameters({"timeToLive"})
    public void setUpClass(int timeToLive) {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilters(filter)
                .build();

        testToken = TestUtils.generateTestToken(id, issuer, secret, subject, timeToLive);

        order = oPr.getOrderWithCommentInstance(
                oPr.getOrderWithCustomerInstance(
                        oPr.getOrderWithDeliveryInstance(
                                oPr.getOrderWithCityIdInstance(
                                        oPr.getOrderWithPayTypeIdInstance(
                                                oPr.getOrderWithDateTimeInstance(
                                                        oPr.getOrderSimpleInstance(
                                                                1L,
                                                                (short) 0),
                                                        LocalDateTime.now()),
                                                (short) 0),
                                        101),
                                dPr.getDeliveryWithDateInstance(
                                        dPr.getDeliveryWithTimeIdInstance(
                                                dPr.getDeliveryWithDepartmentIdInstance(
                                                        dPr.getDeliverySimpleInstance(
                                                                (short) 0,
                                                                101,
                                                                "ул. Пушкина, 145Б"),
                                                        "100"),
                                                (short) 0),
                                        LocalDate.now())),
                        cPr.getCustomerFromCompanyInstance(
                                cPr.getCustomerWithNameInstance(
                                        cPr.getCustomerWithEmailInstance(
                                                cPr.getCustomerWithPhoneNumberInstance(
                                                        cPr.getCustomerSimpleInstance(
                                                                (short) 0),
                                                        "85053452314"),
                                                "example@example.com"),
                                        "John Doe"),
                                "22505",
                                "43122")),
                "Коментарий");

        testBegin("OrdersController");
        writeTestTime("OrdersController");
    }

    @Test(priority = 1)
    void whenValidToken_andProductsIsNull_thenReturns500() throws Exception {
        testMethod("orders() [when valid token and products is null then returns 500]");

        String response = mockMvc.perform(
                post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(order.formForSend()))
                        .header("Authorization", "Bearer " + testToken))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        OrderResponsePojo orderResponsePojo = new OrderResponsePojo();
        orderResponsePojo.setId(0);
        orderResponsePojo.setStatus((short) 500);
        orderResponsePojo.setMessage("Message");

        assertEquals(objectMapper.writeValueAsString(orderResponsePojo), response);
        System.out.println("Response: " + response);
    }

    @Test(priority = 2)
    void whenValidToken_andOrderIsCorrect_thenReturns200WithContent() throws Exception {
        testMethod("orders() [when valid token and order is correct then returns 200 with content]");

        order = oPr.getOrderWithProductsInstance(
                order,
                psPr.getOrderProductsInstance(
                        new ArrayList<>() {{
                            add(pPr.getOrderProductSimpleInstance("id1", 10, 10.99f));
                            add(pPr.getOrderProductSimpleInstance("id2", 20, 400.99f));
                        }}));

        String response = mockMvc.perform(
                post("/orders")
                        .content(objectMapper.writeValueAsString(order.formForSend()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + testToken))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        OrderResponsePojo orderResponsePojo = new OrderResponsePojo();
        orderResponsePojo.setId(0);
        orderResponsePojo.setStatus((short) 0);
        orderResponsePojo.setMessage("OK");

        assertEquals(objectMapper.writeValueAsString(orderResponsePojo), response);
        System.out.println("Response: " + response);
    }

    @Test(priority = 3)
    @Parameters({"incorrectToken"})
    void whenNotValidToken_thenReturns401WithoutContent(String incorrectToken) throws Exception {
        testMethod("orders() [when not valid token then returns 401 without content]");

        System.out.println(mockMvc.perform(
                post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + incorrectToken))
                .andExpect(status().isUnauthorized())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test(priority = 4)
    void whenNotAuthorizationHeader_thenReturns401WithoutContent() throws Exception {
        testMethod("orders() [when not auth header then returns 401 without content]");

        System.out.println(mockMvc.perform(
                post("/orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test(priority = 5)
    @Parameters({"notJWTToken"})
    void whenTokenIsNotJWT_thenReturns401WithoutContent(String notJWTToken) throws Exception {
        testMethod("orders() [when token is not JWT then returns 401 without content]");

        System.out.println(mockMvc.perform(
                post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + notJWTToken))
                .andExpect(status().isUnauthorized())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
        writeTestTime(tr);
    }

    @AfterClass
    public void teardownClass() {
        testEnd("DepartmentsController");
    }
}
