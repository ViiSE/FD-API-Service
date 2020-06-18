package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.DescriptionPojo;

import static org.testng.Assert.*;
import static test.message.TestMessage.*;

public class DescriptionWithProductIdTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Description description;

    private String productId;

    @BeforeClass
    @Parameters({"shortDescription", "productId"})
    public void setUpClass(String shortDescription, String productId) {
        assertNotNull(productId, "ProductId cannot be null!");
        assertFalse(productId.isEmpty(), "ProductId is empty!");

        assertNotNull(shortDescription, "ShortDescription cannot be null!");
        assertFalse(shortDescription.isEmpty(), "ShortDescription is empty!");

        description = new DescriptionWithProductIdImpl(new DescriptionShortImpl(shortDescription), productId);
        assertNotNull(description, "Description is null!");

        this.productId = productId;
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin(DescriptionWithProductIdImpl.class, "formForSend()");

        DescriptionPojo descriptionPojo = (DescriptionPojo) description.formForSend();
        assertNotNull(descriptionPojo, "DescriptionPojo is null!");
        assertEquals(descriptionPojo.getProductId(), productId);
        System.out.println(mapper.writeValueAsString(descriptionPojo));

        testEnd(DescriptionWithProductIdImpl.class, "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
