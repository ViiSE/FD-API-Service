package ru.fd.api.service.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.entity.Attributes;
import ru.fd.api.service.exception.RepositoryException;
import test.repository.AttributesRepositoryTestImpl;

import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

public class AttributesRepositoryTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private AttributesRepository attributesRepository;

    @BeforeClass
    public void setUpClass() {
        attributesRepository = new AttributesRepositoryTestImpl();
    }

    @Test
    public void readProducts() throws JsonProcessingException {
        testBegin("AttributesRepository", "readAttributes()");

        try {
            Attributes attributes = attributesRepository.readAttributes();
            System.out.println("Attributes:");
            String pojo = mapper.writeValueAsString(attributes.formForSend());
            assertTrue(pojo.contains("\"attributes\":"));
            System.out.println(pojo);

        } catch (RepositoryException ex) {
            catchMessage(ex);
        }

        testEnd("AttributesRepository", "readAttributes()");
    }
}
