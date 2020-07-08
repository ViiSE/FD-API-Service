package ru.fd.api.service.process;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.entity.Sendable;
import ru.fd.api.service.exception.ProcessException;

import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class PsDepartmentsIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("psDepartments")
    private Process<Sendable, Void> psDeps;

    @BeforeClass
    public void setUpClass() {
        writeTestTime(PsDepartmentsImpl.class);
    }

    @Test
    public void answer() throws JsonProcessingException {
        testBegin(PsDepartmentsImpl.class, "answer()");

        try {
            Sendable departments = psDeps.answer(null);
            assertNotNull(departments, "Departments is null!");
            System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(departments.formForSend()));
            System.out.println("DONE!");
        } catch (ProcessException ex) {
            catchMessage(ex);
        }

        testEnd(PsDepartmentsImpl.class, "answer()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
        writeTestTime(tr);
    }
}
