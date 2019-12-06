package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.RepositoryException;

import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class ProductsRepositorySimpleIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("productsRepositorySimple")
    private ProductsRepository productsRepository;

    @Test
    public void readProducts() {
        testBegin("ProductsRepositorySimpleIntegration", "readProducts()");

        try {
            Products products = productsRepository.readProducts();
            assertNotNull(products, "Products is null!");
            System.out.println("DONE! ");
        } catch (RepositoryException ex) {
            catchMessage(ex);
        }

        testEnd("ProductsRepositorySimpleIntegration", "readProducts()");
    }
}
