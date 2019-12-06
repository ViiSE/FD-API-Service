package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.data.ProductPojo;
import test.creator.ProductCreatorTestImpl;
import test.creator.ProductStatusesCreatorTestImpl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

public class ProductWithStatusesTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Product product;

    @BeforeClass
    public void setUpClass() {
        product = new ProductCreatorTestImpl().createProduct();
        assertNotNull(product, "Product is null!");
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("ProductWithStatuses", "formForSend()");

        Statuses statuses = new ProductStatusesCreatorTestImpl().create();
        product = new ProductWithStatusesImpl(product, statuses);
        assertNotNull(product, "Product with statuses is null!");

        ProductPojo productPojo = (ProductPojo) product.formForSend();
        assertNotNull(productPojo, "ProductPojo is null!");
        assertNotNull(productPojo.getStatuses(), "Statuses is null!");
        assertTrue(mapper.writeValueAsString(statuses.formForSend()).contains(mapper.writeValueAsString(productPojo.getStatuses())));

        System.out.println(mapper.writeValueAsString(productPojo));

        testEnd("ProductWithStatuses", "formForSend()");
    }
}
