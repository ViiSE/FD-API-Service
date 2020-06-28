package ru.fd.api.service.repository.decorative;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import ru.fd.api.service.repository.ProductsRepository;
import ru.fd.api.service.repository.ProductsRepositoryDecorative;

import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class ProductsRepositoryWithBalancesIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("productsRepositoryWithBalances")
    private ProductsRepositoryDecorative<Products> balancesRepo;

    @Autowired
    @Qualifier("productsRepositorySimple")
    private ProductsRepository simpleRepo;

    @BeforeClass
    public void setUpClass() {
        writeTestTime(ProductsRepositoryWithBalancesImpl.class);
    }

    @Test
    public void read() throws JsonProcessingException {
        testBegin(ProductsRepositoryWithBalancesImpl.class, "read()");

        try {
            Products products = balancesRepo.read(simpleRepo.read());
            assertNotNull(products, "Products is null!");
            System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(products.formForSend()));
            System.out.println("DONE!");
        } catch (RepositoryException ex) {
            catchMessage(ex);
        }

        testEnd(ProductsRepositoryWithBalancesImpl.class, "read()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
        writeTestTime(tr);
    }
}
