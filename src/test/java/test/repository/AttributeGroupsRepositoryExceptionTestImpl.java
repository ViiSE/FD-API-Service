package test.repository;

import ru.fd.api.service.creator.AttributeGroupsCreator;
import ru.fd.api.service.entity.AttributeGroups;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.repository.AttributeGroupsRepository;
import test.creator.AttributeGroupsCreatorTestImpl;

public class AttributeGroupsRepositoryExceptionTestImpl implements AttributeGroupsRepository {

    @Override
    public AttributeGroups readAttributeGroups() throws RepositoryException {
        throw new RepositoryException("EXCEPTION! :(");
    }
}
