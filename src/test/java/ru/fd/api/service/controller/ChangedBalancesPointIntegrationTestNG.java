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
import ru.fd.api.service.data.ProductChangedBalancesPojo;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.filter.APIFilter;
import ru.fd.api.service.process.Process;
import test.util.TestUtils;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.assertEquals;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class ChangedBalancesPointIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired private WebApplicationContext context;
    @Autowired private APIFilter filter;
    @Autowired private ObjectMapper objectMapper;

    @Autowired
    @Qualifier("psChangedBalances")
    private Process<Products, Void> pcBalanceAll;

    @Autowired
    @Qualifier("psChangedBalancesWithOrder")
    private Process<Products, Long> pcBalanceOrder;

    @Value("${fd.api.service.jwt-id}")      private String id;
    @Value("${fd.api.service.jwt-issuer}")  private String issuer;
    @Value("${fd.api.service.jwt-secret}")  private String secret;
    @Value("${fd.api.service.jwt-subject}") private String subject;

    private MockMvc mockMvc;
    private String testToken;

    @BeforeClass
    @Parameters({"timeToLive"})
    public void setUpClass(int timeToLive) {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilters(filter)
                .build();

        testToken = TestUtils.generateTestToken(id, issuer, secret, subject, timeToLive);

        testBegin("ProductsController [/products/changes/balances/ point]");
        writeTestTime("ProductsController");
    }

    @Test(priority = 1)
    void whenValidToken_and_noOrderId_thenReturns200WithContent() throws Exception {
        testMethod("changedBalances() [when valid token then returns 200 with content]");

        String response = mockMvc.perform(
                get("/products/changes/balances")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + testToken))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        ProductChangedBalancesPojo productP = (ProductChangedBalancesPojo) pcBalanceAll.answer(null).formForSend();

        assertEquals(response, objectMapper.writeValueAsString(productP));
    }

    @Test(priority = 2)
    void whenValidToken_and_orderId_thenReturns200WithContent() throws Exception {
        testMethod("changedBalances() [when valid token then returns 200 with content]");

        String response = mockMvc.perform(
                get("/products/changes/balances")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + testToken)
                        .requestAttr("order_id", 1L))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        ProductChangedBalancesPojo productP = (ProductChangedBalancesPojo) pcBalanceOrder.answer(1L).formForSend();

        assertEquals(response, objectMapper.writeValueAsString(productP));
    }

    @Test(priority = 3)
    @Parameters({"incorrectToken"})
    void whenNotValidToken_thenReturns401WithoutContent(String incorrectToken) throws Exception {
        testMethod("changedBalances() [when not valid token then returns 401 without content]");

        System.out.println(mockMvc.perform(
                get("/products/changes/balances")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + incorrectToken))
                .andExpect(status().isUnauthorized())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test(priority = 4)
    void whenNotAuthorizationHeader_thenReturns401WithoutContent() throws Exception {
        testMethod("changedBalances() [when not auth header then returns 401 without content]");

        System.out.println(mockMvc.perform(
                get("/products/changes/balances")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test(priority = 5)
    @Parameters({"notJWTToken"})
    void whenTokenIsNotJWT_thenReturns401WithoutContent(String notJWTToken) throws Exception {
        testMethod("changedBalances() [when token is not JWT then returns 401 without content]");

        System.out.println(mockMvc.perform(
                get("/products/changes/balances")
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
        testEnd("ProductsController");
    }
}
