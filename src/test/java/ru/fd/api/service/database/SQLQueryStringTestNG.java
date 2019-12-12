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

package ru.fd.api.service.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.producer.database.SQLQueryProducer;

import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class SQLQueryStringTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    private SQLQueryProducer sqlQueryProducer;

    private SQLQuery<String> sqlQuery;

    @BeforeClass
    @Parameters({"content"})
    public void setUpClass(String content) {
        SQLQuery<String> sqlQuery = sqlQueryProducer.getSQLQueryStringInstance(content);
        assertNotNull(sqlQuery, "SQLQuery is null!");
    }


    @Test
    public void content() {
        testBegin("SQLQueryString", "content()");

        String content = sqlQuery.content();
        assertNotNull(content, "Content is null!");
        System.out.println("Content: " + content);

        testEnd("SQLQueryString", "content()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
