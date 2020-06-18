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

public class DescriptionFullTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Description description;

    private String fullDescription;

    @BeforeClass
    @Parameters({"shortDescription", "fullDescription"})
    public void setUpClass(String shortDescription, String fullDescription) {
        assertNotNull(fullDescription, "FullDescription cannot be null!");
        assertFalse(fullDescription.isEmpty(), "FullDescription is empty!");

        assertNotNull(shortDescription, "ShortDescription cannot be null!");
        assertFalse(shortDescription.isEmpty(), "ShortDescription is empty!");


        description = new DescriptionFullImpl(new DescriptionShortImpl(shortDescription), fullDescription);
        assertNotNull(description, "Description is null!");

        this.fullDescription = fullDescription;
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin(DescriptionFullImpl.class, "formForSend()");

        DescriptionPojo descriptionPojo = (DescriptionPojo) description.formForSend();
        assertNotNull(descriptionPojo, "DescriptionPojo is null!");
        assertEquals(descriptionPojo.getFullDescription(), fullDescription);
        System.out.println(mapper.writeValueAsString(descriptionPojo));

        testEnd(DescriptionFullImpl.class, "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
