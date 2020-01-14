package test.repository;

import ru.fd.api.service.creator.DepartmentsCreator;
import ru.fd.api.service.entity.Departments;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.repository.DepartmentsRepository;
import test.creator.DepartmentsCreatorTestImpl;

public class DepartmentsRepositoryTestImpl implements DepartmentsRepository {

    @Override
    public Departments readDepartments() {
        DepartmentsCreator dpCr = new DepartmentsCreatorTestImpl();
        try {
            return dpCr.create();
        } catch (CreatorException ignore) {}
        return null;
    }
}
