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

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class PsChainLgProductsIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("psChainLgProducts")
    private Process<Products, List<String>> psChProds;

    private List<String> with;

    @BeforeClass
    public void setUpClass() {
        writeTestTime(PsChainLgProductsImpl.class);
        with = new ArrayList<>() {{ add("descriptions"); }};
    }

    @Test
    public void answer() throws JsonProcessingException {
        testBegin(PsChainLgProductsImpl.class, "answer()");

        try {
            Products products = psChProds.answer(with);
            assertNotNull(products, "Products is null!");
            System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(products.formForSend()));
            System.out.println("DONE!");
        } catch (ProcessException ex) {
            catchMessage(ex);
        }

        testEnd(PsChainLgProductsImpl.class, "answer()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
        writeTestTime(tr);
    }
}
