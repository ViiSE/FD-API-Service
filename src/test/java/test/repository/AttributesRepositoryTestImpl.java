package test.repository;

import ru.fd.api.service.creator.AttributeGroupsCreator;
import ru.fd.api.service.creator.AttributesCreator;
import ru.fd.api.service.entity.AttributeGroups;
import ru.fd.api.service.entity.Attributes;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.repository.AttributeGroupsRepository;
import ru.fd.api.service.repository.AttributesRepository;
import test.creator.AttributeGroupsCreatorTestImpl;
import test.creator.AttributesCreatorTestImpl;

public class AttributesRepositoryTestImpl implements AttributesRepository {

    @Override
    public Attributes readAttributes() throws RepositoryException {
        AttributesCreator attrCr = new AttributesCreatorTestImpl();
        try {
            return attrCr.create();
        } catch (CreatorException ignore) {}
        return null;
    }
}
