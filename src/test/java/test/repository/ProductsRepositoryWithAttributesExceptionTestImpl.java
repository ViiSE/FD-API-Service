package test.repository;

import ru.fd.api.service.entity.Categories;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.repository.CategoriesRepository;
import ru.fd.api.service.repository.ProductsRepository;

public class ProductsRepositoryWithAttributesExceptionTestImpl implements ProductsRepository {

    @Override
    public Products readProducts() throws RepositoryException {
        throw new RepositoryException("EXCEPTION! :(");
    }
}
