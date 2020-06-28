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
import ru.fd.api.service.data.DeliveryPojo;
import ru.fd.api.service.entity.Delivery;
import ru.fd.api.service.exception.ProcessException;

import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class PsDeliveryIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("psDelivery")
    private Process<Delivery, DeliveryPojo> psDel;

    private DeliveryPojo delPojo;

    @BeforeClass
    public void setUpClass() {
        writeTestTime(PsDeliveryImpl.class);

        delPojo = new DeliveryPojo((short) 0, 101, "example st.");
    }

    @Test
    public void readCategories() throws JsonProcessingException {
        testBegin(PsDeliveryImpl.class, "answer()");

        try {
            Delivery delivery = psDel.answer(delPojo);
            assertNotNull(delivery, "Delivery is null!");
            System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(delivery.formForSend()));
            System.out.println("DONE!");
        } catch (ProcessException ex) {
            catchMessage(ex);
        }

        testEnd(PsDeliveryImpl.class, "answer()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
        writeTestTime(tr);
    }
}
