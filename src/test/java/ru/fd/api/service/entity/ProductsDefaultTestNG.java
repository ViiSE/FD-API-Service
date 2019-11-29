package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.data.ProductsPojo;
import test.producer.ProductCreatorTestPDTImpl;
import test.producer.ProductsCreatorTestImpl;

import static org.testng.Assert.*;
import static test.message.TestMessage.*;

public class ProductsDefaultTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Products products;
    private Product product;

    @BeforeClass
    public void setUpClass() {
        products = new ProductsCreatorTestImpl().create();
        product = new ProductCreatorTestPDTImpl().createProduct();

        assertNotNull(products, "Products is null!");
        testBegin("ProductsDefault");
    }

    @Test(priority = 1)
    public void formForSend() throws JsonProcessingException {
        testMethod("formForSend()");

        ProductsPojo productsPojo = (ProductsPojo) products.formForSend();
        assertNotNull(productsPojo, "ProductPojo is null!");
        assertNotNull(productsPojo.getProducts());

        System.out.println(mapper.writeValueAsString(productsPojo));
    }

    @Test(priority = 2)
    public void findProductById() throws JsonProcessingException {
        testMethod("findProductById()");

        Product foundProduct = products.findProductById(product.id());
        assertNotNull(foundProduct, "Found Product is null!");
        assertEquals(mapper.writeValueAsString(foundProduct.formForSend()), mapper.writeValueAsString(product.formForSend()));

        System.out.println("--Product to be found--");
        System.out.println(mapper.writeValueAsString(product.formForSend()));
        System.out.println("--Found product--");
        System.out.println(mapper.writeValueAsString(foundProduct.formForSend()));
    }

    @Test(priority = 3)
    public void decorateProduct() throws JsonProcessingException {
        testMethod("decorateProduct()");

        Products oldProducts = new ProductsCreatorTestImpl().create();

        System.out.println("--Old product--");
        System.out.println(mapper.writeValueAsString(product.formForSend()));

        Product productWithFullDescription = new ProductWithFullDescriptionImpl(product, "Full description");

        System.out.println("--New product--");
        System.out.println(mapper.writeValueAsString(productWithFullDescription.formForSend()));

        products.decorateProduct(product.id(), productWithFullDescription);
        assertEquals(productWithFullDescription, products.findProductById(productWithFullDescription.id()));
        assertNotEquals(mapper.writeValueAsString(oldProducts.formForSend()), mapper.writeValueAsString(products.formForSend()));

        System.out.println("--New products--");
        System.out.println(mapper.writeValueAsString(products.formForSend()));
    }

    @AfterTest
    public void teardownClass() {
        testEnd("ProductsDefault");
    }
}
