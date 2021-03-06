package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.CategoryPojo;

import static org.testng.Assert.*;
import static test.message.TestMessage.*;

public class CategoryTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Category category;

    private String categoryId;
    private String name;

    @BeforeClass
    @Parameters({"categoryId", "name"})
    public void setUpClass(String categoryId, String name) {
        assertNotNull(categoryId, "Category ID cannot be null!");
        assertFalse(categoryId.isEmpty(), "Category ID is empty!");

        assertNotNull(name, "Category name cannot be null!");
        assertFalse(name.isEmpty(), "Category name is empty!");

        category = new CategoryImpl(categoryId, name);
        assertNotNull(category, "Category is null!");

        this.categoryId = categoryId;
        this.name = name;
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin(CategoryImpl.class, "formForSend()");

        CategoryPojo categoryPojo = (CategoryPojo) category.formForSend();
        assertNotNull(categoryPojo, "CategoryPojo is null!");
        assertEquals(categoryPojo.getId(), categoryId);
        assertEquals(categoryPojo.getName(), name);
        System.out.println(mapper.writeValueAsString(categoryPojo));

        testEnd(CategoryImpl.class, "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
