/*
 *  Copyright 2019 ViiSE.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
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
import ru.fd.api.service.data.CategoriesPojo;
import ru.fd.api.service.entity.Sendable;
import ru.fd.api.service.filter.APIFilter;
import ru.fd.api.service.process.Process;
import test.util.TestUtils;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.assertEquals;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class ProductsCategoriesPointIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired private WebApplicationContext context;
    @Autowired private APIFilter filter;

    @Autowired private ObjectMapper objectMapper;

    @Autowired
    @Qualifier("psCategories")
    private Process<Sendable, Void> process;

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

        testBegin("ProductsAdditionalController [GET /products/categories]");
        writeTestTime("ProductsAdditionalController [GET /products/categories]");
    }

    @Test(priority = 1)
    void whenValidToken_thenReturns200WithContent() throws Exception {
        testMethod("categories() [when valid token then returns 200 without content]");

        String response = mockMvc.perform(
                get("/products/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + testToken))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        CategoriesPojo catPojo = (CategoriesPojo) process.answer(null).formForSend();

        assertEquals(response, objectMapper.writeValueAsString(catPojo));
    }

    @Test(priority = 2)
    @Parameters({"incorrectToken"})
    void whenNotValidToken_thenReturns401WithoutContent(String incorrectToken) throws Exception {
        testMethod("categories() [when not valid token then returns 401 without content]");

        System.out.println(mockMvc.perform(
                get("/products/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + incorrectToken))
                .andExpect(status().isUnauthorized())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test(priority = 3)
    void whenNotAuthorizationHeader_thenReturns401WithoutContent() throws Exception {
        testMethod("categories() [when not auth header then returns 401 without content]");

        System.out.println(mockMvc.perform(
                get("/products/categories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test(priority = 4)
    @Parameters({"notJWTToken"})
    void whenTokenIsNotJWT_thenReturns401WithoutContent(String notJWTToken) throws Exception {
        testMethod("categories() [when token is not JWT then returns 401 without content]");

        System.out.println(mockMvc.perform(
                get("/products/categories")
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
        testEnd("ProductsAdditionalController [GET /products/categories]\"");
    }
}
