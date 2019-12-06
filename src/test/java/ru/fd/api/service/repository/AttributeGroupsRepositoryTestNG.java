package ru.fd.api.service.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.entity.AttributeGroups;
import ru.fd.api.service.exception.RepositoryException;
import test.repository.AttributeGroupsRepositoryTestImpl;

import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

public class AttributeGroupsRepositoryTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private AttributeGroupsRepository attributeGroupsRepository;

    @BeforeClass
    public void setUpClass() {
        attributeGroupsRepository = new AttributeGroupsRepositoryTestImpl();
    }

    @Test
    public void readProducts() throws JsonProcessingException {
        testBegin("AttributeGroupsRepository", "readAttributeGroups()");

        try {
            AttributeGroups attributeGroups = attributeGroupsRepository.readAttributeGroups();
            System.out.println("AttributeGroups:");
            String pojo = mapper.writeValueAsString(attributeGroups.formForSend());
            assertTrue(pojo.contains("\"attribute_groups\":"));
            System.out.println(pojo);

        } catch (RepositoryException ex) {
            catchMessage(ex);
        }

        testEnd("AttributeGroupsRepository", "readAttributeGroups()");
    }
}
