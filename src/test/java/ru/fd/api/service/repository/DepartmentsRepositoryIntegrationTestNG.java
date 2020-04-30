package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.entity.Departments;
import ru.fd.api.service.exception.RepositoryException;

import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class DepartmentsRepositoryIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("departmentsRepository")
    private DepartmentsRepository departmentsRepository;

    @BeforeClass
    public void setUpClass() {
        writeTestTime("DepartmentsRepositoryIntegration");
    }

    @Test
    public void readDepartments() {
        testBegin("DepartmentsRepositoryIntegration", "read()");

        try {
            Departments departments = departmentsRepository.read();
            assertNotNull(departments, "Departments is null!");
            System.out.println("DONE!");
        } catch (RepositoryException ex) {
            catchMessage(ex);
        }

        testEnd("DepartmentsRepositoryIntegration", "read()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
        writeTestTime(tr);
    }
}
