package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.entity.Units;
import ru.fd.api.service.exception.RepositoryException;

import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class UnitsRepositoryDefaultIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("unitsRepositoryDefault")
    private UnitsRepository unitsRepository;

    @Test
    public void readUnits() {
        testBegin("UnitsRepositoryDefaultIntegration", "readUnits()");

        try {
            Units units = unitsRepository.readUnits();
            assertNotNull(units, "Units is null!");
            System.out.println("DONE!");
        } catch (RepositoryException ex) {
            catchMessage(ex);
        }

        testEnd("DepartmentsRepositoryDefaultIntegration", "readUnits()");
    }
}
