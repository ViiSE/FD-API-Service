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
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.ProcessException;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class PsOrderProductsIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("psOrderProducts")
    private Process<Products, OrderPojo> psOProd;

    private OrderPojo orderPojo;

    @BeforeClass
    public void setUpClass() {
        writeTestTime(PsOrderProductsImpl.class);

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
        testBegin(PsOrderProductsImpl.class, "answer()");

        try {
            Products products = psOProd.answer(orderPojo);
            assertNotNull(products, "Products is null!");
            System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(products.formForSend()));
            System.out.println("DONE!");
        } catch (ProcessException ex) {
            catchMessage(ex);
        }

        testEnd(PsOrderProductsImpl.class, "answer()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
        writeTestTime(tr);
    }
}
