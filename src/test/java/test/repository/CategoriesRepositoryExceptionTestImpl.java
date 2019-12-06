package test.repository;

import ru.fd.api.service.entity.Categories;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.repository.CategoriesRepository;

public class CategoriesRepositoryExceptionTestImpl implements CategoriesRepository {

    @Override
    public Categories readCategories() throws RepositoryException {
        throw new RepositoryException("EXCEPTION! :(");
    }
}
