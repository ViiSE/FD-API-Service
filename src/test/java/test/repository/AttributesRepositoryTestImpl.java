package test.repository;

import ru.fd.api.service.creator.AttributesCreator;
import ru.fd.api.service.entity.Attributes;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.repository.AttributesRepository;
import test.creator.AttributesCreatorTestImpl;

public class AttributesRepositoryTestImpl implements AttributesRepository {

    @Override
    public Attributes readAttributes() {
        AttributesCreator attrCr = new AttributesCreatorTestImpl();
        try {
            return attrCr.create();
        } catch (CreatorException ignore) {}
        return null;
    }
}
