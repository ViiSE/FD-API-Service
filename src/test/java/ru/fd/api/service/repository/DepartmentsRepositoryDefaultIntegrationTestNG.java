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
public class DepartmentsRepositoryDefaultIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("departmentsRepositoryDefault")
    private DepartmentsRepository departmentsRepository;

    @BeforeClass
    public void setUpClass() {
        writeTestTime("DepartmentsRepositoryDefaultIntegration");
    }

    @Test
    public void readDepartments() {
        testBegin("DepartmentsRepositoryDefaultIntegration", "readDepartments()");

        try {
            Departments departments = departmentsRepository.readDepartments();
            assertNotNull(departments, "Departments is null!");
            System.out.println("DONE!");
        } catch (RepositoryException ex) {
            catchMessage(ex);
        }

        testEnd("DepartmentsRepositoryDefaultIntegration", "readDepartments()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
        writeTestTime(tr);
    }
}
