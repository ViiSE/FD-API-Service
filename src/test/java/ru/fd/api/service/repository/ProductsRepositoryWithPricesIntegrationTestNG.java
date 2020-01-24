package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.PriceProducer;
import ru.fd.api.service.producer.entity.PricesProducer;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.repository.ProductsRepositoryProducer;

import java.time.LocalDateTime;

import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class ProductsRepositoryWithPricesIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired private ProductsRepositoryProducer productsRepositoryProducer;
    @Autowired private ProductProducer productProducer;
    @Autowired private PriceProducer priceProducer;
    @Autowired private PricesProducer pricesProducer;
    @Autowired private SQLQueryCreator<String, String> sqlQueryCreator;

    @Test
    public void readProducts() {
        testBegin("ProductsRepositoryWithPricesIntegration", "readProducts() [" + LocalDateTime.now() + "]");

        try {
            Products products = productsRepositoryProducer.getProductsRepositoryWithPricesInstance(
                    productsRepositoryProducer.getProductsRepositorySimpleInstance(
                            productProducer,
                            sqlQueryCreator),
                    productProducer,
                    priceProducer,
                    pricesProducer,
                    sqlQueryCreator)
                    .read();
            assertNotNull(products, "Products is null!");
            System.out.println("DONE! [" + LocalDateTime.now() + "]");
            //System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(products.formForSend()));
        } catch (RepositoryException ex) { //| JsonProcessingException ex) {
            catchMessage(ex);
        }

        testEnd("ProductsRepositoryWithPricesIntegration", "readProducts() [" + LocalDateTime.now() + "]");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
