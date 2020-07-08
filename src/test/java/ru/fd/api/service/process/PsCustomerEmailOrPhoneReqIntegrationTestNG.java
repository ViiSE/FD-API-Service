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
import ru.fd.api.service.data.CustomerPojo;
import ru.fd.api.service.entity.Customer;
import ru.fd.api.service.exception.ProcessException;

import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class PsCustomerEmailOrPhoneReqIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("psCustomerEmailOrPhoneReq")
    private Process<Customer, CustomerPojo> psCsReq;

    private CustomerPojo csPojo;

    @BeforeClass
    public void setUpClass() {
        writeTestTime(PsCustomerEmailOrPhoneReqImpl.class);

        csPojo = new CustomerPojo();
        csPojo.setEmail("example@example.com");
    }

    @Test
    public void answer() throws JsonProcessingException {
        testBegin(PsCustomerEmailOrPhoneReqImpl.class, "answer()");

        try {
            Customer cs = psCsReq.answer(csPojo);
            assertNotNull(cs, "Customer is null!");
            System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(cs.formForSend()));
            System.out.println("DONE!");
        } catch (ProcessException ex) {
            catchMessage(ex);
        }

        testEnd(PsCustomerEmailOrPhoneReqImpl.class, "answer()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
        writeTestTime(tr);
    }
}
