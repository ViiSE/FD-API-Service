package test.repository;

import ru.fd.api.service.creator.UnitsCreator;
import ru.fd.api.service.entity.Units;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.repository.UnitsRepository;
import test.creator.UnitsCreatorTestImpl;

public class UnitsRepositoryTestImpl implements UnitsRepository {

    @Override
    public Units readUnits() {
        UnitsCreator uniCr = new UnitsCreatorTestImpl();
        try {
            return uniCr.create();
        } catch (CreatorException ignore) {}
        return null;
    }
}
