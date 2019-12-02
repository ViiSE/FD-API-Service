package test.repository;

import ru.fd.api.service.creator.ProductsCreator;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.repository.ProductsRepository;
import test.creator.ProductsCreatorTestImpl;

public class ProductsRepositorySimpleTestImpl implements ProductsRepository {

    @Override
    public Products readProducts() {
        ProductsCreator productsCreator = new ProductsCreatorTestImpl();
        try {
            return productsCreator.create();
        } catch (CreatorException ignore) {}
        return null;
    }
}
