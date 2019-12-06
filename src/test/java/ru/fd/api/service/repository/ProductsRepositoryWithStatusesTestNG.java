package ru.fd.api.service.repository;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.entity.*;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.ProductProducer;
import test.producer.entity.ProductProducerTestImpl;
import test.repository.ProductsRepositorySimpleTestImpl;
import test.repository.ProductsRepositoryWithStatusesTestImpl;

import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

public class ProductsRepositoryWithStatusesTestNG {

    private ProductsRepository productsRepository;

    @BeforeClass
    public void setUpClass() {
        ProductsRepository prRepoSimple = new ProductsRepositorySimpleTestImpl();
        ProductProducer productProducer = new ProductProducerTestImpl();
        productsRepository = new ProductsRepositoryWithStatusesTestImpl(prRepoSimple, productProducer);
    }

    @Test
    public void readProducts() {
        testBegin("ProductRepositoryWithStatuses", "readProducts()");

        try {
            Products products = productsRepository.readProducts();
            System.out.println("Products:");
            for(int i = 1; i < 10; i++) {
                Product pr = products.findProductById("id_" + i);
                assertTrue(pr instanceof ProductWithStatusesImpl, "Products is not with statuses!");
                System.out.println(pr);
            }
        } catch (RepositoryException ex) {
            catchMessage(ex);
        }
        testEnd("ProductRepositoryWithStatuses", "readProducts()");
    }
}
