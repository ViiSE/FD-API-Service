package test.repository;

import ru.fd.api.service.entity.Departments;
import ru.fd.api.service.entity.Units;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.repository.DepartmentsRepository;
import ru.fd.api.service.repository.UnitsRepository;

public class UnitsRepositoryExceptionTestImpl implements UnitsRepository {

    @Override
    public Units readUnits() throws RepositoryException {
        throw new RepositoryException("EXCEPTION! :(");
    }
}
