package ru.fd.api.service.repository;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.entity.Product;
import ru.fd.api.service.entity.ProductWithShortDescriptionImpl;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.ProductProducer;
import test.producer.entity.ProductProducerTestImpl;
import test.repository.ProductsRepositorySimpleTestImpl;
import test.repository.ProductsRepositoryWithShortDescriptionTestImpl;

import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

public class ProductsRepositoryWithShortDescriptionTestNG {

    private ProductsRepository productsRepository;

    @BeforeClass
    public void setUpClass() {
        ProductsRepository prRepoSimple = new ProductsRepositorySimpleTestImpl();
        ProductProducer productProducer = new ProductProducerTestImpl();
        productsRepository = new ProductsRepositoryWithShortDescriptionTestImpl(prRepoSimple, productProducer);
    }

    @Test
    public void readProducts() {
        testBegin("ProductRepositoryWithShortDescription", "readProducts()");

        try {
            Products products = productsRepository.read();
            System.out.println("Products:");
            for(int i = 1; i < 10; i++) {
                Product pr = products.findProductById("id_" + i);
                assertTrue(pr instanceof ProductWithShortDescriptionImpl, "Products is not with short description!");
                System.out.println(pr);
            }
        } catch (RepositoryException ex) {
            catchMessage(ex);
        }
        testEnd("ProductRepositoryWithShortDescription", "readProducts()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
