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
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.ProcessException;

import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class PsChangedBalancesWithOrderIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("psChangedBalancesWithOrder")
    private Process<Products, Long> psChBalancesWOrder;

    @BeforeClass
    public void setUpClass() {
        writeTestTime(PsChangedBalancesWithOrderImpl.class);
    }

    @Test
    public void readCategories() throws JsonProcessingException {
        testBegin(PsChangedBalancesWithOrderImpl.class, "answer()");

        try {
            Products products = psChBalancesWOrder.answer(1L);
            assertNotNull(products, "Products is null!");
            System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(products.formForSend()));
            System.out.println("DONE!");
        } catch (ProcessException ex) {
            catchMessage(ex);
        }

        testEnd(PsChangedBalancesWithOrderImpl.class, "answer()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
        writeTestTime(tr);
    }
}
