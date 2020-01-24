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
import ru.fd.api.service.creator.DepartmentsCreator;
import ru.fd.api.service.data.DepartmentsPojo;
import ru.fd.api.service.filter.APIFilter;
import test.util.TestUtils;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.assertEquals;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class DepartmentsControllerIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired private WebApplicationContext context;
    @Autowired private APIFilter filter;

    @Autowired private ObjectMapper objectMapper;
    @Autowired private DepartmentsCreator departmentsCreator;

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

        testBegin("DepartmentsController");
        writeTestTime("DepartmentsController");
    }

    @Test(priority = 1)
    void whenValidToken_thenReturns200WithContent() throws Exception {
        testMethod("departments() [when valid token then returns 200 with content]");

        String response = mockMvc.perform(
                get("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + testToken))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        DepartmentsPojo depPojo = (DepartmentsPojo) departmentsCreator.create().formForSend();

        assertEquals(response, objectMapper.writeValueAsString(depPojo));
        System.out.println("Response: " + response);
    }

    @Test(priority = 2)
    @Parameters({"incorrectToken"})
    void whenNotValidToken_thenReturns401WithoutContent(String incorrectToken) throws Exception {
        testMethod("departments() [when not valid token then returns 401 without content]");

        System.out.println(mockMvc.perform(
                get("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + incorrectToken))
                .andExpect(status().isUnauthorized())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test(priority = 3)
    void whenNotAuthorizationHeader_thenReturns401WithoutContent() throws Exception {
        testMethod("departments() [when not auth header then returns 401 without content]");

        System.out.println(mockMvc.perform(
                get("/departments")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test(priority = 4)
    @Parameters({"notJWTToken"})
    void whenTokenIsNotJWT_thenReturns401WithoutContent(String notJWTToken) throws Exception {
        testMethod("departments() [when token is not JWT then returns 401 without content]");

        System.out.println(mockMvc.perform(
                get("/departments")
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
