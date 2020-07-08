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
public class PsLgAttributesIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("psLgAttributes")
    private Process<Sendable, Void> psLgAttrs;

    @BeforeClass
    public void setUpClass() {
        writeTestTime(PsLgAttributesImpl.class);
    }

    @Test
    public void answer() throws JsonProcessingException {
        testBegin(PsLgAttributesImpl.class, "answer()");

        try {
            Sendable attrGroups = psLgAttrs.answer(null);
            assertNotNull(attrGroups, "Attributes is null!");
            System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(attrGroups.formForSend()));
            System.out.println("DONE!");
        } catch (ProcessException ex) {
            catchMessage(ex);
        }

        testEnd(PsLgAttributesImpl.class, "answer()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
        writeTestTime(tr);
    }
}
