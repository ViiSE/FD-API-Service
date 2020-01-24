package test.repository;

import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.repository.ProductsRepository;

public class ProductsRepositoryWithAttributesExceptionTestImpl implements ProductsRepository {

    @Override
    public Products read() throws RepositoryException {
        throw new RepositoryException("EXCEPTION! :(");
    }
}
