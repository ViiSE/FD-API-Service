package test.producer.repository;

import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.repository.ProductsRepositoryProducer;
import ru.fd.api.service.repository.ProductsRepository;
import test.repository.ProductsRepositoryWithAttributesExceptionTestImpl;

public class ProductsRepositoryProducerExceptionTestImpl implements ProductsRepositoryProducer {

    @Override
    public ProductsRepository getProductsRepositorySimpleInstance() {
        return null;
    }

    @Override
    public ProductsRepository getProductsRepositoryWithAttributesInstance(
            ProductsRepository productsRepository, ProductProducer productProducer) {
        return new ProductsRepositoryWithAttributesExceptionTestImpl();
    }

    @Override
    public ProductsRepository getProductsRepositoryWithBalancesInstance(ProductsRepository productsRepository, ProductProducer productProducer) {
        return null;
    }

    @Override
    public ProductsRepository getProductsRepositoryWithPricesInstance(ProductsRepository productsRepository, ProductProducer productProducer) {
        return null;
    }

    @Override
    public ProductsRepository getProductsRepositoryWithStatusesInstance(ProductsRepository productsRepository, ProductProducer productProducer) {
        return null;
    }

    @Override
    public ProductsRepository getProductsRepositoryWithShortDescriptionInstance(ProductsRepository productsRepository, ProductProducer productProducer) {
        return null;
    }

    @Override
    public ProductsRepository getProductsRepositoryWithFullDescriptionInstance(ProductsRepository productsRepository, ProductProducer productProducer) {
        return null;
    }
}
