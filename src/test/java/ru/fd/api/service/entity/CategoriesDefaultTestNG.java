package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.CategoriesPojo;

import java.util.ArrayList;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

public class CategoriesDefaultTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Categories categories;

    @BeforeClass
    @Parameters({"categoryId1", "name1", "categoryId2", "name2"})
    public void setUpClass(String categoryId1, String name1, String categoryId2, String name2) {
        assertNotNull(categoryId1, "Category ID cannot be null!");
        assertFalse(categoryId1.isEmpty(), "Category ID is empty!");
        assertNotNull(name1, "Category name cannot be null!");
        assertFalse(name1.isEmpty(), "Category name is empty!");

        assertNotNull(categoryId2, "Category ID cannot be null!");
        assertFalse(categoryId2.isEmpty(), "Category ID is empty!");
        assertNotNull(name2, "Category name cannot be null!");
        assertFalse(name2.isEmpty(), "Category name is empty!");

        Category category1 = new CategoryDefaultImpl(categoryId1, name1);
        Category category2 = new CategoryDefaultImpl(categoryId2, name2);

        categories = new CategoriesDefaultImpl(new ArrayList<>() {{ add(category1); add(category2); }});
        assertNotNull(categories, "Categories is null!");
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("CategoriesDefault", "formForSend()");

        CategoriesPojo categoriesPojo = (CategoriesPojo) categories.formForSend();
        assertNotNull(categoriesPojo, "CategoriesPojo is null!");
        System.out.println(mapper.writeValueAsString(categoriesPojo));

        testEnd("CategoriesDefault", "formForSend()");
    }
}
