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
import org.testng.annotations.*;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.exception.ReaderException;
import ru.fd.api.service.producer.database.SQLQueryProducer;
import ru.fd.api.service.producer.database.SQLReaderProducer;
import ru.fd.api.service.producer.util.FDAPIServiceDirectoryProducer;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class SQLReaderFromFileTestNG extends AbstractTestNGSpringContextTests {

    @Autowired private FDAPIServiceDirectoryProducer fdapiServiceDirectoryProducer;
    @Autowired private SQLReaderProducer sqlReaderProducer;
    @Autowired private SQLQueryProducer sqlQueryProducer;

    @BeforeClass
    public void setUpClass() {
        testBegin("SQLReaderFromFile");
    }

    private SQLReader createSQLReader(String filename) {
        SQLReader sqlReader =
                sqlReaderProducer.getSQLReaderFromFileStringInstance(
                        fdapiServiceDirectoryProducer.getFdAPIServiceCurrentDirectoryInstance(),
                        sqlQueryProducer,
                        filename);
        assertNotNull(sqlReader, "SQLReader is null!");
        return sqlReader;
    }

    private void test(String fileName) {
        try {
            SQLQuery sqlQuery = createSQLReader(fileName).read();
            assertNotNull(sqlQuery, "SQLQuery is null!");
            String content = (String) sqlQuery.content();
            assertFalse(content.isEmpty(), "SQLContent is empty!");
            System.out.println(content);
        } catch (ReaderException ex) {
            catchMessage(ex);
        }
    }

    @Test
    @Parameters({"fileNameSuccess"})
    public void read_success(String fileName) {
        testMethod("read() [success]");
        test(fileName);
    }

    @Test
    @Parameters({"fileNameFileNotFound"})
    public void read_fileIsNotFound(String fileName) {
        testMethod("read() [fileIsNotFound]");
        test(fileName);
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }

    @AfterClass
    public void teardownClass() {
        testEnd("SQLReaderFromFile");
    }
}
