package ru.fd.api.service.repository.decorative;

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
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.*;
import ru.fd.api.service.repository.ProductsRepository;
import ru.fd.api.service.repository.ProductsRepositoryDecorative;

import java.util.ArrayList;

import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class ProductsRepositoryWithChangedBalancesAndOrderIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("productsRepositoryWithChangedBalancesAndOrder")
    private ProductsRepositoryDecorative<Order> orderRepo;

    @Autowired
    @Qualifier("productsRepositorySimple")
    private ProductsRepository simpleRepo;

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
        writeTestTime(ProductsRepositoryWithBalancesImpl.class);

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
    public void read() throws JsonProcessingException {
        testBegin(ProductsRepositoryWithBalancesImpl.class, "read()");

        try {
            Products products = orderRepo.read(order);
            assertNotNull(products, "Products is null!");
            System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(products.formForSend()));
            System.out.println("DONE!");
        } catch (RepositoryException ex) {
            catchMessage(ex);
        }

        testEnd(ProductsRepositoryWithBalancesImpl.class, "read()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
        writeTestTime(tr);
    }
}
