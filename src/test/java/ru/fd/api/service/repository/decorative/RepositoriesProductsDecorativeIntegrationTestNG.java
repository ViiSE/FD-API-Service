package ru.fd.api.service.repository.decorative;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.constant.RepositoriesProducts;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.repository.ProductsRepositoryDecorative;

import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class RepositoriesProductsDecorativeIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("repositoriesProductsDecorative")
    private Repositories<ProductsRepositoryDecorative<Products>> decoRepos;

    @BeforeClass
    public void setUpClass() {
        writeTestTime(ProductsRepositoryWithStatusesImpl.class);
    }

    @Test
    public void read() {
        testBegin(ProductsRepositoryWithPricesImpl.class, "read()");

        ProductsRepositoryDecorative<Products> attrRepo = decoRepos.repo(RepositoriesProducts.ATTRIBUTES);
        assertNotNull(attrRepo, "Attributes repo is null!");

        ProductsRepositoryDecorative<Products> balRepo = decoRepos.repo(RepositoriesProducts.BALANCES);
        assertNotNull(balRepo, "Balances repo is null!");

        ProductsRepositoryDecorative<Products> descRepo = decoRepos.repo(RepositoriesProducts.DESCRIPTIONS);
        assertNotNull(descRepo, "Descriptions repo is null!");

        ProductsRepositoryDecorative<Products> pricesRepo = decoRepos.repo(RepositoriesProducts.PRICES);
        assertNotNull(pricesRepo, "Prices repo is null!");

        ProductsRepositoryDecorative<Products> statsRepo = decoRepos.repo(RepositoriesProducts.STATUSES);
        assertNotNull(statsRepo, "Statuses repo is null!");

        System.out.println("DONE!");

        testEnd(ProductsRepositoryWithStatusesImpl.class, "read()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
        writeTestTime(tr);
    }
}
