package ru.fd.api.service.process;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.constant.AdditionalProducts;
import ru.fd.api.service.entity.Sendable;

import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class PsProductAdditionalIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("psProductAdditional")
    private Processes<Sendable, Void> psDeps;

    @BeforeClass
    public void setUpClass() {
        writeTestTime(PsProductAdditionalImpl.class);
    }

    @Test
    public void answer() throws JsonProcessingException {
        testBegin(PsProductAdditionalImpl.class, "answer()");

        Process<Sendable, Void> unitsProc = psDeps.process(AdditionalProducts.UNITS);
        assertNotNull(unitsProc, "UnitsProc is null!");

        Process<Sendable, Void> catsProc = psDeps.process(AdditionalProducts.CATEGORIES);
        assertNotNull(catsProc, "CategoriesProc is null!");

        Process<Sendable, Void> attrGrsProc = psDeps.process(AdditionalProducts.ATTRIBUTE_GROUPS);
        assertNotNull(attrGrsProc, "AttributeGroupsProc is null!");

        Process<Sendable, Void> attrsProc = psDeps.process(AdditionalProducts.ATTRIBUTES);
        assertNotNull(attrsProc, "AttributesProc is null!");

        Process<Sendable, Void> statsGrsProc = psDeps.process(AdditionalProducts.STATUSES);
        assertNotNull(statsGrsProc, "StatusesProc is null!");

        System.out.println("DONE!");

        testEnd(PsProductAdditionalImpl.class, "answer()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
        writeTestTime(tr);
    }
}
