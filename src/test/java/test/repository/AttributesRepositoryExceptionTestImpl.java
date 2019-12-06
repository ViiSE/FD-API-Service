package test.repository;

import ru.fd.api.service.entity.Attributes;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.repository.AttributesRepository;

public class AttributesRepositoryExceptionTestImpl implements AttributesRepository {

    @Override
    public Attributes readAttributes() throws RepositoryException {
        throw new RepositoryException("EXCEPTION! :(");
    }
}
