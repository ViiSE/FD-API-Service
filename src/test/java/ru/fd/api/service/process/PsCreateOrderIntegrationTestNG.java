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
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.entity.OrderResponse;
import ru.fd.api.service.exception.ProcessException;
import ru.fd.api.service.producer.entity.*;

import java.util.ArrayList;

import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class PsCreateOrderIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("psCreateOrder")
    private Process<OrderResponse, Order> psCrOrder;

    @Autowired
    private OrderProducer oProd;

    @Autowired
    private ProductsProducer psProd;

    @Autowired
    private ProductProducer pProd;

    @Autowired
    private DeliveryProducer dProd;

    @Autowired
    private CustomerProducer cProd;

    private Order order;

    @BeforeClass
    public void setUpClass() {
        writeTestTime(PsCreateOrderImpl.class);

        order = oProd.getOrderWithCityIdInstance(
                oProd.getOrderWithCustomerInstance(
                        oProd.getOrderWithDeliveryInstance(
                                oProd.getOrderWithProductsInstance(
                                        oProd.getOrderWithPayTypeIdInstance(
                                                oProd.getOrderSimpleInstance(
                                                        1L,
                                                        (short) 0
                                                ),
                                                (short) 0
                                        ),
                                        psProd.getOrderProductsInstance(
                                                new ArrayList<>() {{
                                                    pProd.getOrderProductSimpleInstance(
                                                            "1",
                                                            1,
                                                            1.0f
                                                    );}})
                                        ),
                                dProd.getDeliverySimpleInstance((short) 0, 101, "st. example")
                                ),
                        cProd.getCustomerWithEmailInstance(
                                cProd.getCustomerSimpleInstance((short) 0),
                                "example@example.com"
                        )),
                101);
    }

    @Test
    public void answer() throws JsonProcessingException {
        testBegin(PsCreateOrderImpl.class, "answer()");

        try {
            OrderResponse re = psCrOrder.answer(order);
            assertNotNull(re, "OrderResponse is null!");
            System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(re.formForSend()));
            System.out.println("DONE!");
        } catch (ProcessException ex) {
            catchMessage(ex);
        }

        testEnd(PsCreateOrderImpl.class, "answer()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
        writeTestTime(tr);
    }
}
