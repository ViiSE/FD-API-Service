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
import ru.fd.api.service.entity.Units;
import ru.fd.api.service.exception.RepositoryException;

import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class UnitsRepositoryIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("unitsRepository")
    private UnitsRepository unitsRepository;

    @BeforeClass
    public void setUpCLass() {
        writeTestTime("UnitsRepositoryIntegration");
    }

    @Test
    public void readUnits() {
        testBegin("UnitsRepositoryIntegration", "read()");

        try {
            Units units = unitsRepository.read();
            assertNotNull(units, "Units is null!");
            System.out.println("DONE!");
        } catch (RepositoryException ex) {
            catchMessage(ex);
        }

        testEnd("UnitsRepositoryIntegration", "read()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
        writeTestTime(tr);
    }
}
