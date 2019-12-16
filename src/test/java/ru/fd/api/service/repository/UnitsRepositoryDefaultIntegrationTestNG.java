package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Units;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.UnitProducer;
import ru.fd.api.service.producer.entity.UnitsProducer;
import ru.fd.api.service.producer.repository.UnitsRepositoryProducer;

import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class UnitsRepositoryDefaultIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired private UnitsRepositoryProducer unitsRepositoryProducer;
    @Autowired private UnitProducer unitProducer;
    @Autowired private UnitsProducer unitsProducer;
    @Autowired private SQLQueryCreator<String, String> sqlQueryCreator;

    @BeforeClass
    public void setUpCLass() {
        writeTestTime("UnitsRepositoryDefaultIntegration");
    }

    @Test
    public void readUnits() {
        testBegin("UnitsRepositoryDefaultIntegration", "readUnits()");

        try {
            Units units = unitsRepositoryProducer.getUnitsRepositoryDefaultInstance(
                    unitProducer,
                    unitsProducer,
                    sqlQueryCreator)
                    .readUnits();
            assertNotNull(units, "Units is null!");
            System.out.println("DONE!");
        } catch (RepositoryException ex) {
            catchMessage(ex);
        }

        testEnd("DepartmentsRepositoryDefaultIntegration", "readUnits()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
        writeTestTime(tr);
    }
}
