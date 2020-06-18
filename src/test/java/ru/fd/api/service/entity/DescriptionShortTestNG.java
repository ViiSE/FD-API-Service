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

public class DescriptionShortTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Description description;

    private String shortDescription;

    @BeforeClass
    @Parameters({"shortDescription"})
    public void setUpClass(String shortDescription) {
        assertNotNull(shortDescription, "ShortDescription cannot be null!");
        assertFalse(shortDescription.isEmpty(), "ShortDescription is empty!");

        description = new DescriptionShortImpl(shortDescription);
        assertNotNull(description, "Description is null!");

        this.shortDescription = shortDescription;
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin(DescriptionShortImpl.class, "formForSend()");

        DescriptionPojo descriptionPojo = (DescriptionPojo) description.formForSend();
        assertNotNull(descriptionPojo, "DescriptionPojo is null!");
        assertEquals(descriptionPojo.getShortDescription(), shortDescription);
        System.out.println(mapper.writeValueAsString(descriptionPojo));

        testEnd(DescriptionShortImpl.class, "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
