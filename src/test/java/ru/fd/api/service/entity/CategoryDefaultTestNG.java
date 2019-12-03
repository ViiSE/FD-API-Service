package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.BalancePojo;
import ru.fd.api.service.data.CategoryPojo;

import static org.testng.Assert.*;
import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

public class CategoryDefaultTestNG {

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

        category = new CategoryDefaultImpl(categoryId, name);
        assertNotNull(category, "Category is null!");

        this.categoryId = categoryId;
        this.name = name;
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("CategoryDefault", "formForSend()");

        CategoryPojo categoryPojo = (CategoryPojo) category.formForSend();
        assertNotNull(categoryPojo, "CategoryPojo is null!");
        assertEquals(categoryPojo.getId(), categoryId);
        assertEquals(categoryPojo.getName(), name);
        System.out.println(mapper.writeValueAsString(categoryPojo));

        testEnd("CategoryDefault", "formForSend()");
    }
}
