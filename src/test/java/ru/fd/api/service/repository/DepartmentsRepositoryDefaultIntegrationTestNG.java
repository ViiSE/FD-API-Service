package ru.fd.api.service.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.entity.Departments;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.RepositoryException;

import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class DepartmentsRepositoryDefaultIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("departmentsRepositoryDefault")
    private DepartmentsRepository departmentsRepository;

    @Test
    public void readDepartments() {
        testBegin("DepartmentsRepositoryDepartmentsIntegration", "readDepartments()");

        try {
            Departments departments = departmentsRepository.readDepartments();
            assertNotNull(departments, "Departments is null!");
            System.out.println("DONE!");
        } catch (RepositoryException ex) {
            catchMessage(ex);
        }

        testEnd("DepartmentsRepositoryDepartmentsIntegration", "readDepartments()");
    }
}
