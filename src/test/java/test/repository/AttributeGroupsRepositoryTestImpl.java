package test.repository;

import ru.fd.api.service.creator.AttributeGroupsCreator;
import ru.fd.api.service.entity.AttributeGroups;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.repository.AttributeGroupsRepository;
import test.creator.AttributeGroupsCreatorTestImpl;

public class AttributeGroupsRepositoryTestImpl implements AttributeGroupsRepository {

    @Override
    public AttributeGroups readAttributeGroups() throws RepositoryException {
        AttributeGroupsCreator attrGrCr = new AttributeGroupsCreatorTestImpl();
        try {
            return attrGrCr.create();
        } catch (CreatorException ignore) {}
        return null;
    }
}
