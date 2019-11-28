package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.ProductPojo;
import ru.fd.api.service.data.ProductsPojo;
import test.producer.ProductsCreatorTestImpl;

import static org.testng.Assert.*;
import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

public class ProductsDefaultTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Products products;

    @BeforeClass
    public void setUpClass() {
        products = new ProductsCreatorTestImpl().createProducts();
        assertNotNull(products, "Products is null!");
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("ProductsDefault", "formForSend()");

        ProductsPojo productsPojo = (ProductsPojo) products.formForSend();
        assertNotNull(productsPojo, "ProductPojo is null!");
        assertNotNull(productsPojo.getProducts());

        System.out.println(mapper.writeValueAsString(productsPojo));

        testEnd("ProductsDefault", "formForSend()");
    }
}
