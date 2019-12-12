package ru.fd.api.service.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.SQLQueryCreatorService;
import ru.fd.api.service.entity.Categories;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.CategoriesProducer;
import ru.fd.api.service.producer.entity.CategoryProducer;
import ru.fd.api.service.producer.repository.CategoriesRepositoryProducer;

import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class CategoriesRepositoryDefaultIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired private CategoriesRepositoryProducer categoriesRepositoryProducer;
    @Autowired private CategoryProducer categoryProducer;
    @Autowired private CategoriesProducer categoriesProducer;
    @Autowired private SQLQueryCreatorService sqlQueryCreatorService;

    @Test
    public void readCategories() throws JsonProcessingException {
        testBegin("CategoriesRepositoryDepartmentsIntegration", "readCategories()");

        try {
            Categories categories = categoriesRepositoryProducer.getCategoriesRepositoryDefaultInstance(
                    categoryProducer,
                    categoriesProducer,
                    sqlQueryCreatorService.sqlQueryCreatorFromFileString())
                    .readCategories();
            assertNotNull(categories, "Categories is null!");
//            System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(categories.formForSend()));
            System.out.println("DONE!");
        } catch (RepositoryException ex) {
            catchMessage(ex);
        }

        testEnd("CategoriesRepositoryDepartmentsIntegration", "readCategories()");
    }
}
