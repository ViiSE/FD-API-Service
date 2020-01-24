package ru.fd.api.service.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.entity.Categories;
import ru.fd.api.service.exception.RepositoryException;

import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class CategoriesRepositoryDefaultIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("categoriesRepositoryDefault")
    private CategoriesRepository categoriesRepository;

    @BeforeClass
    public void setUpClass() {
        writeTestTime("CategoriesRepositoryDefaultIntegration");
    }

    @Test
    public void readCategories() throws JsonProcessingException {
        testBegin("CategoriesRepositoryDefaultIntegration", "readCategories()");

        try {
            Categories categories = categoriesRepository.readCategories();
            assertNotNull(categories, "Categories is null!");
            System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(categories.formForSend()));
            System.out.println("DONE!");
        } catch (RepositoryException ex) {
            catchMessage(ex);
        }

        testEnd("CategoriesRepositoryDefaultIntegration", "readCategories()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
        writeTestTime(tr);
    }
}
