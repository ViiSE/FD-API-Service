package test.repository;

import ru.fd.api.service.entity.Categories;
import ru.fd.api.service.entity.Departments;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.repository.CategoriesRepository;
import ru.fd.api.service.repository.DepartmentsRepository;

public class DepartmentsRepositoryExceptionTestImpl implements DepartmentsRepository {

    @Override
    public Departments readDepartments() throws RepositoryException {
        throw new RepositoryException("EXCEPTION! :(");
    }
}
