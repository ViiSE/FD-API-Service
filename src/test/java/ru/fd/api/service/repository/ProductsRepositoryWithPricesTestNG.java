package ru.fd.api.service.repository;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.entity.*;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.ProductProducer;
import test.producer.entity.ProductProducerTestImpl;
import test.repository.ProductsRepositorySimpleTestImpl;
import test.repository.ProductsRepositoryWithPricesTestImpl;

import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

public class ProductsRepositoryWithPricesTestNG {

    private ProductsRepository productsRepository;

    @BeforeClass
    public void setUpClass() {
        ProductsRepository prRepoSimple = new ProductsRepositorySimpleTestImpl();
        ProductProducer productProducer = new ProductProducerTestImpl();
        productsRepository = new ProductsRepositoryWithPricesTestImpl(prRepoSimple, productProducer);
    }

    @Test
    public void readProducts() {
        testBegin("ProductRepositoryWithPrices", "readProducts()");

        try {
            Products products = productsRepository.readProducts();
            System.out.println("Products:");
            for(int i = 1; i < 10; i++) {
                Product pr = products.findProductById("id_" + i);
                if(i == 1 || i == 2)
                    assertTrue(pr instanceof ProductWithPricesImpl, "Products is not with prices!");
                else
                    assertTrue(pr instanceof ProductSimpleImpl, "Products is not simple!");
                System.out.println(pr);
            }
        } catch (RepositoryException ex) {
            catchMessage(ex);
        }
        testEnd("ProductRepositoryWithPrices", "readProducts()");
    }
}
