package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.*;
import ru.fd.api.service.producer.repository.ProductsRepositoryProducer;

import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class ProductsRepositoryWithBalancesAndPricesIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired private ProductsRepositoryProducer productsRepositoryProducer;
    @Autowired private ProductProducer productProducer;
    @Autowired private BalanceProducer balanceProducer;
    @Autowired private BalancesProducer balancesProducer;
    @Autowired private PriceProducer priceProducer;
    @Autowired private PricesProducer pricesProducer;
    @Autowired private AttributeGroupsProducer attributeGroupsProducer;
    @Autowired private SQLQueryCreator<String, String> sqlQueryCreator;

    @Test
    public void readProducts() {
        testBegin("ProductsRepositoryWithBalancesAndPricesIntegration", "readProducts()");

        try {
            ProductsRepository productsRepoSimple = productsRepositoryProducer
                    .getProductsRepositorySimpleInstance(
                            productProducer,
                            sqlQueryCreator);

            ProductsRepository productsRepoWB = productsRepositoryProducer
                    .getProductsRepositoryWithBalancesInstance(
                            productsRepoSimple,
                            productProducer,
                            balanceProducer,
                            balancesProducer,
                            sqlQueryCreator);

            Products products = productsRepositoryProducer
                    .getProductsRepositoryWithPricesInstance(
                            productsRepoWB,
                            productProducer,
                            priceProducer,
                            pricesProducer,
                            sqlQueryCreator)
                    .readProducts();
            assertNotNull(products, "Products is null!");
            System.out.println("DONE! ");
        } catch (RepositoryException ex) {
            catchMessage(ex);
        }

        testEnd("ProductsRepositoryWithBalancesAndPricesIntegration", "readProducts()");
    }
}
