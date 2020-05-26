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

package ru.fd.api.service.producer.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.entity.Department;
import ru.fd.api.service.entity.DepartmentImpl;

import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class DepartmentProducerIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    private DepartmentProducer departmentProducer;

    @Test
    @Parameters({"id", "name"})
    public void getDepartmentInstance(String id, String name) {
        testBegin(DepartmentProducerImpl.class, "getDepartmentInstance()");

        Department department = departmentProducer.getDepartmentInstance(id, name);
        assertTrue(department instanceof DepartmentImpl, "Department: not a valid type!");
        System.out.println("Instance: " + department);

        testEnd(DepartmentProducerImpl.class, "getDepartmentInstance()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
