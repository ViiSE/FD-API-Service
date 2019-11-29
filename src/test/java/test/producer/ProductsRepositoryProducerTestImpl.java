package test.producer;

import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.repository.ProductsRepositoryProducer;
import ru.fd.api.service.repository.ProductsRepository;
import test.repository.*;

public class ProductsRepositoryProducerTestImpl implements ProductsRepositoryProducer {

    @Override
    public ProductsRepository getProductsRepositorySimpleInstance() {
        return new ProductsRepositorySimpleTestImpl();
    }

    @Override
    public ProductsRepository getProductsRepositoryWithAttributesInstance(
            ProductsRepository productsRepository, ProductProducer productProducer) {
        return new ProductsRepositoryWithAttributesTestImpl(productsRepository, productProducer);
    }

    @Override
    public ProductsRepository getProductsRepositoryWithBalancesInstance(ProductsRepository productsRepository, ProductProducer productProducer) {
        return new ProductsRepositoryWithBalancesTestImpl(productsRepository, productProducer);
    }

    @Override
    public ProductsRepository getProductsRepositoryWithPricesInstance(ProductsRepository productsRepository, ProductProducer productProducer) {
        return new ProductsRepositoryWithPricesTestImpl(productsRepository, productProducer);
    }

    @Override
    public ProductsRepository getProductsRepositoryWithStatusesInstance(ProductsRepository productsRepository, ProductProducer productProducer) {
        return new ProductsRepositoryWithStatusesTestImpl(productsRepository, productProducer);
    }

    @Override
    public ProductsRepository getProductsRepositoryWithShortDescriptionInstance(ProductsRepository productsRepository, ProductProducer productProducer) {
        return new ProductsRepositoryWithShortDescriptionTestImpl(productsRepository, productProducer);
    }

    @Override
    public ProductsRepository getProductsRepositoryWithFullDescriptionInstance(ProductsRepository productsRepository, ProductProducer productProducer) {
        return new ProductsRepositoryWithFullDescriptionTestImpl(productsRepository, productProducer);
    }
}
