package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Product;
import ru.fd.api.service.entity.ProductWithAttributesImpl;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.AttributeGroupProducer;
import ru.fd.api.service.producer.entity.AttributeGroupsProducer;
import ru.fd.api.service.producer.entity.ProductProducer;
import test.producer.entity.ProductProducerTestImpl;
import test.repository.ProductsRepositorySimpleTestImpl;
import test.repository.ProductsRepositoryWithAttributesTestImpl;

import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

public class ProductsRepositoryWithAttributesTestNG {

    private ProductsRepository productsRepository;

    @BeforeClass
    public void setUpClass() {
        ProductsRepository prRepoSimple = new ProductsRepositorySimpleTestImpl();
        ProductProducer productProducer = new ProductProducerTestImpl();
        productsRepository = new ProductsRepositoryWithAttributesTestImpl(prRepoSimple, productProducer);
    }

    @Test
    public void readProducts() {
        testBegin("ProductRepositoryWithAttributes", "readProducts()");

        try {
            Products products = productsRepository.readProducts();
            System.out.println("Products:");
            for(int i = 1; i < 10; i++) {
                Product pr = products.findProductById("id_" + i);
                assertTrue(pr instanceof ProductWithAttributesImpl, "Products is not with attributes!");
                System.out.println(pr);
            }
        } catch (RepositoryException ex) {
            catchMessage(ex);
        }
        testEnd("ProductRepositoryWithAttributes", "readProducts()");
    }
}
