package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.SQLQueryCreatorService;
import ru.fd.api.service.entity.Departments;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.DepartmentProducer;
import ru.fd.api.service.producer.entity.DepartmentsProducer;
import ru.fd.api.service.producer.repository.DepartmentsRepositoryProducer;

import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class DepartmentsRepositoryDefaultIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired private DepartmentsRepositoryProducer departmentsRepositoryProducer;
    @Autowired private DepartmentProducer departmentProducer;
    @Autowired private DepartmentsProducer departmentsProducer;
    @Autowired private SQLQueryCreatorService sqlQueryCreatorService;

    @Test
    public void readDepartments() {
        testBegin("DepartmentsRepositoryDepartmentsIntegration", "readDepartments()");

        try {
            Departments departments = departmentsRepositoryProducer.getDepartmentsRepositoryDefaultInstance(
                    departmentProducer,
                    departmentsProducer,
                    sqlQueryCreatorService.sqlQueryCreatorFromFileString())
                    .readDepartments();
            assertNotNull(departments, "Departments is null!");
            System.out.println("DONE!");
        } catch (RepositoryException ex) {
            catchMessage(ex);
        }

        testEnd("DepartmentsRepositoryDepartmentsIntegration", "readDepartments()");
    }
}
