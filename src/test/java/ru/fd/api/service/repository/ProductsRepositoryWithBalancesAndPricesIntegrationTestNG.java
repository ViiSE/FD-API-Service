package ru.fd.api.service.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.RepositoryException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class ProductsRepositoryWithBalancesAndPricesIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("productsRepositoryWithBalances")
    private ProductsRepositoryDecorative<Products> repoProductsWithBalances;

    @Autowired
    @Qualifier("productsRepositoryWithPrices")
    private ProductsRepositoryDecorative<Products> repoProductsWithPrices;

    @Autowired
    @Qualifier("productsRepositorySimple")
    private ProductsRepository repoProducts;

    private Products balancesPricesProducts;
    private Products pricesBalancesProducts;

    @BeforeClass
    public void setUpCLass() {
        testBegin("ProductsRepositoryWithBalancesAndPricesIntegration");
        writeTestTime("ProductsRepositoryWithBalancesAndPricesIntegration");
    }

    @Test(priority = 1)
    public void readProducts_firstBalancesThenPrices() {
        testMethod( "readProducts() [first balances then prices]");

        try {
            balancesPricesProducts = repoProductsWithPrices.read(
                    repoProductsWithBalances.read(
                            repoProducts.read()
                    ));
            assertNotNull(balancesPricesProducts, "Products is null!");
            System.out.println("DONE! ");
        } catch (RepositoryException ex) {
            catchMessage(ex);
        }
    }

    @Test(priority = 2)
    public void readProducts_firstPricesThenBalances() {
        testMethod( "readProducts() [first prices then balances]");

        try {
            pricesBalancesProducts = repoProductsWithBalances.read(
                    repoProductsWithPrices.read(
                            repoProducts.read()
                    ));
            assertNotNull(pricesBalancesProducts, "Products is null!");
            System.out.println("DONE! ");
        } catch (RepositoryException ex) {
            catchMessage(ex);
        }
    }

    @Test(priority = 3, suiteName = "readProducts_firstBalancesThenPricesEqualsFirstPricesThenBalances")
    public void readProducts_firstBalancesThenPricesEqualsFirstPricesThenBalances() throws JsonProcessingException {
        testMethod( "readProducts() [{first balances then prices} equals {first prices then balances}]");

        ObjectMapper mapper = new ObjectMapper();

        assertEquals(
                mapper.readTree(mapper.writeValueAsString(balancesPricesProducts.formForSend())),
                mapper.readTree(mapper.writeValueAsString(pricesBalancesProducts.formForSend())),
                "Not equals!");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
        writeTestTime(tr);
    }

    @AfterClass
    public void teardownClass() {
        testEnd("ProductsRepositoryWithBalancesAndPricesIntegration");
    }
}
