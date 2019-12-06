package test.repository;

import ru.fd.api.service.entity.AttributeGroups;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.repository.AttributeGroupsRepository;

public class AttributeGroupsRepositoryExceptionTestImpl implements AttributeGroupsRepository {

    @Override
    public AttributeGroups readAttributeGroups() throws RepositoryException {
        throw new RepositoryException("EXCEPTION! :(");
    }
}
