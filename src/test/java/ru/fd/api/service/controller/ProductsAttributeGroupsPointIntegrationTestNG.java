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
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
import ru.fd.api.service.AttributeGroupsService;
import ru.fd.api.service.SQLQueryCreatorService;
import ru.fd.api.service.data.AttributeGroupsPojo;
import ru.fd.api.service.filter.APIFilter;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.assertEquals;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)

public class ProductsAttributeGroupsPointIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired private WebApplicationContext context;
    @Autowired private APIFilter filter;

    @Autowired private ObjectMapper objectMapper;
    @Autowired private AttributeGroupsService attributeGroupsService;
    @Autowired private SQLQueryCreatorService sqlQueryCreatorService;

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

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMills = System.currentTimeMillis();
        Date now = new Date(nowMills);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder jwtBuilder = Jwts.builder()
                .setIssuedAt(now)
                .setId(id)
                .setIssuer(issuer)
                .setSubject(subject)
                .signWith(signatureAlgorithm, signingKey);

        if(timeToLive >= 0) {
            long expMills = nowMills + timeToLive;
            Date exp = new Date(expMills);
            jwtBuilder.setExpiration(exp);
        }

        testToken = jwtBuilder.compact();

        testBegin("ProductsAdditionalController [GET /products/attribute-groups]");
    }

    @Test(priority = 1)
    void whenValidToken_thenReturns200WithContent() throws Exception {
        testMethod("attributeGroups() [when valid token then returns 200 with content]");

        String response = mockMvc.perform(
                get("/products/attribute-groups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + testToken))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        AttributeGroupsPojo attrGrPojo = (AttributeGroupsPojo) attributeGroupsService.attributeGroupsCreatorProducer()
                .getAttributeGroupsCreatorDefaultInstance(
                        attributeGroupsService.attributeGroupsRepositoryProducer()
                                .getAttributeGroupsRepositoryDefaultInstance(
                                        attributeGroupsService.attributeGroupProducer(),
                                        attributeGroupsService.attributeGroupsProducer(),
                                        sqlQueryCreatorService.sqlQueryCreatorFromFileString()))
                .create()
                .formForSend();

        assertEquals(response, objectMapper.writeValueAsString(attrGrPojo));
    }

    @Test(priority = 2)
    @Parameters({"incorrectToken"})
    void whenNotValidToken_thenReturns200WithoutContent(String incorrectToken) throws Exception {
        testMethod("attributeGroups() [when not valid token then returns 200 without content]");

        System.out.println(mockMvc.perform(
                get("/products/attribute-groups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + incorrectToken))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test(priority = 3)
    void whenNotAuthorizationHeader_thenReturns200WithoutContent() throws Exception {
        testMethod("attributeGroups() [when not auth header then returns 200 without content]");

        System.out.println(mockMvc.perform(
                get("/products/attribute-groups")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test(priority = 4)
    @Parameters({"notJWTToken"})
    void whenTokenIsNotJWT_thenReturns200WithoutContent(String notJWTToken) throws Exception {
        testMethod("attributeGroups() [when token is not JWT then returns 200 without content]");

        System.out.println(mockMvc.perform(
                get("/products/attribute-groups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + notJWTToken))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }

    @AfterClass
    public void teardownClass() {
        testEnd("ProductsAdditionalController [GET /products/attribute-groups]\"");
    }
}
