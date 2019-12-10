package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.repository.ProductsRepositoryProducer;

import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class ProductsRepositoryWithBalancesAndPricesIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("productsRepositoryProducerDefault")
    private ProductsRepositoryProducer productsRepositoryProducer;

    @Autowired
    @Qualifier("productsProducerDefault")
    private ProductProducer productProducer;

    @Test
    public void readProducts() {
        testBegin("ProductsRepositoryWithBalancesAndPricesIntegration", "readProducts()");

        try {
            ProductsRepository productsRepoWB = productsRepositoryProducer
                    .getProductsRepositoryWithBalancesInstance(
                            productsRepositoryProducer.getProductsRepositorySimpleInstance(), productProducer);

            Products products = productsRepositoryProducer
                    .getProductsRepositoryWithPricesInstance(productsRepoWB,productProducer)
                    .readProducts();
            assertNotNull(products, "Products is null!");
            System.out.println("DONE! ");
        } catch (RepositoryException ex) {
            catchMessage(ex);
        }

        testEnd("ProductsRepositoryWithBalancesAndPricesIntegration", "readProducts()");
    }
}