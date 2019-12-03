package test.repository;

import ru.fd.api.service.creator.AttributesCreator;
import ru.fd.api.service.creator.CategoriesCreator;
import ru.fd.api.service.entity.Attributes;
import ru.fd.api.service.entity.Categories;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.repository.AttributesRepository;
import ru.fd.api.service.repository.CategoriesRepository;
import test.creator.AttributesCreatorTestImpl;
import test.creator.CategoriesCreatorTestImpl;

public class CategoriesRepositoryTestImpl implements CategoriesRepository {

    @Override
    public Categories readCategories() throws RepositoryException {
        CategoriesCreator catCr = new CategoriesCreatorTestImpl();
        try {
            return catCr.create();
        } catch (CreatorException ignore) {}
        return null;
    }
}
