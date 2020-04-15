package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.RepositoryException;

import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class ProductsRepositoryWithBalancesIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("productsRepositoryWithBalances")
    private ProductsRepositoryDecorative<Products> repoProductsWithBalances;

    @Autowired
    @Qualifier("productsRepositorySimple")
    private ProductsRepository repoProducts;

    @BeforeClass
    public void setUpCLass() {
        writeTestTime("ProductsRepositoryWithBalancesIntegration");
    }

    @Test
    public void readProducts() {
        testBegin("ProductsRepositoryWithBalancesIntegration", "readProducts()");

        try {
            Products products = repoProductsWithBalances.read(
                    repoProducts.read());
            assertNotNull(products, "Products is null!");
            System.out.println("DONE! ");
        } catch (RepositoryException ex) {
            catchMessage(ex);
        }

        testEnd("ProductsRepositoryWithBalancesIntegration", "readProducts()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
        writeTestTime(tr);
    }
}
