package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.data.ProductPojo;
import test.creator.BalancesCreatorTestImpl;
import test.creator.ProductCreatorTestImpl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

public class ProductWithBalancesTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Product product;

    @BeforeClass
    public void setUpClass() {
        product = new ProductCreatorTestImpl().createProduct();
        assertNotNull(product, "Product is null!");
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("ProductWithBalances", "formForSend()");

        Balances balances = new BalancesCreatorTestImpl().createBalances();
        product = new ProductWithBalancesImpl(product, balances);
        assertNotNull(product, "Product with balances is null!");

        ProductPojo productPojo = (ProductPojo) product.formForSend();
        assertNotNull(productPojo, "ProductPojo is null!");
        assertNotNull(productPojo.getBalances(), "Balances is null!");
        assertTrue(mapper.writeValueAsString(balances.formForSend()).contains(mapper.writeValueAsString(productPojo.getBalances())));

        System.out.println(mapper.writeValueAsString(productPojo));

        testEnd("ProductWithBalances", "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
