package ru.fd.api.service.producer.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.ProductsRepository;

@Service("productsRepositoryProducerDefault")
public class ProductsRepositoryProducerDefaultImpl implements ProductsRepositoryProducer {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public ProductsRepository getProductsRepositorySimpleInstance() {
        return ctx.getBean("productsRepositorySimple", ProductsRepository.class);
    }

    @Override
    public ProductsRepository getProductsRepositoryWithAttributesInstance(
            ProductsRepository productsRepository, ProductProducer productProducer) {
        return (ProductsRepository) ctx.getBean("productsRepositoryWithAttributes", productsRepository, productProducer);
    }

    @Override
    public ProductsRepository getProductsRepositoryWithBalancesInstance(ProductsRepository productsRepository, ProductProducer productProducer) {
        return (ProductsRepository) ctx.getBean("productsRepositoryWithBalances", productsRepository, productProducer);
    }

    @Override
    public ProductsRepository getProductsRepositoryWithPricesInstance(ProductsRepository productsRepository, ProductProducer productProducer) {
        return (ProductsRepository) ctx.getBean("productsRepositoryWithPrices", productsRepository, productProducer);
    }

    @Override
    public ProductsRepository getProductsRepositoryWithStatusesInstance(ProductsRepository productsRepository, ProductProducer productProducer) {
        return (ProductsRepository) ctx.getBean("productsRepositoryWithStatuses", productsRepository, productProducer);
    }

    @Override
    public ProductsRepository getProductsRepositoryWithShortDescriptionInstance(ProductsRepository productsRepository, ProductProducer productProducer) {
        return (ProductsRepository) ctx.getBean("productsRepositoryWithShortDescription", productsRepository, productProducer);
    }

    @Override
    public ProductsRepository getProductsRepositoryWithFullDescriptionInstance(ProductsRepository productsRepository, ProductProducer productProducer) {
        return (ProductsRepository) ctx.getBean("productsRepositoryWithFullDescription", productsRepository, productProducer);
    }
}
