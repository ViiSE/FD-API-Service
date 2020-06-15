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

public class CategoryWithParentIdTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Category category;
    private String parentId;

    @BeforeClass
    @Parameters({"categoryId", "name", "parentId"})
    public void setUpClass(String categoryId, String name, String parentId) {
        assertNotNull(categoryId, "Category ID cannot be null!");
        assertFalse(categoryId.isEmpty(), "Category ID is empty!");

        assertNotNull(name, "Category name cannot be null!");
        assertFalse(name.isEmpty(), "Category name is empty!");

        assertNotNull(parentId, "ParentId cannot be null!");
        assertFalse(parentId.isEmpty(), "ParentId name is empty!");

        category = new CategoryWithParentIdImpl(
                new CategoryImpl(categoryId, name),
                parentId);
        assertNotNull(category, "Category is null!");

        this.parentId = parentId;
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin(CategoryWithParentIdImpl.class, "formForSend()");

        CategoryPojo categoryPojo = (CategoryPojo) category.formForSend();
        assertNotNull(categoryPojo, "CategoryPojo is null!");
        assertEquals(categoryPojo.getParentId(), parentId);
        System.out.println(mapper.writeValueAsString(categoryPojo));

        testEnd(CategoryWithParentIdImpl.class, "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
