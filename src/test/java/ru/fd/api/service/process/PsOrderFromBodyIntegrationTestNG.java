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
import ru.fd.api.service.data.DeliveryPojo;
import ru.fd.api.service.data.OrderPojo;
import ru.fd.api.service.data.ProductOrderPojo;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.exception.ProcessException;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class PsOrderFromBodyIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("psOrderFromBody")
    private Process<Order, OrderPojo> psOrderFromBody;

    private OrderPojo orderPojo;

    @BeforeClass
    public void setUpClass() {
        writeTestTime(PsChainLgCreateOrderImpl.class);
        orderPojo = new OrderPojo(1L);
        orderPojo.setCityId(101);
        orderPojo.setCustomer(new CustomerPojo() {{
            setType((short)0);
            setEmail("example@example.com");
            setPhoneNumber("89999999999"); }});
        orderPojo.setDelivery(new DeliveryPojo(
                (short)0,
                101,
                "st. Example") {{
            setDeliveryDate(LocalDate.now());
            setDeliveryTimeId((short) 0);
        }});
        orderPojo.setProducts(new ArrayList<>() {{
            add(new ProductOrderPojo("1", 1, 1.0f));
        }});
        orderPojo.setPayTypeId((short) 0);
    }

    @Test
    public void answer() throws JsonProcessingException {
        testBegin(PsChainLgCreateOrderImpl.class, "answer()");

        try {
            Order order = psOrderFromBody.answer(orderPojo);
            assertNotNull(order, "Order is null!");
            System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(order.formForSend()));
            System.out.println("DONE!");
        } catch (ProcessException ex) {
            catchMessage(ex);
        }

        testEnd(PsChainLgCreateOrderImpl.class, "answer()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
        writeTestTime(tr);
    }
}
