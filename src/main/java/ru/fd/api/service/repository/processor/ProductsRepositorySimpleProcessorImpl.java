package ru.fd.api.service.repository.processor;

import org.springframework.stereotype.Component;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.repository.ProductsRepositoryProducer;
import ru.fd.api.service.repository.ProductsRepository;

@Component("productsRepositorySimpleProcessor")
public class ProductsRepositorySimpleProcessorImpl implements ProductsRepositoryProcessor {

    private final ProductsRepositoryProducer prodsRepoProducer;
    private final ProductProducer productProducer;
    private final SQLQueryCreator<String, String> sqlQueryCreator;

    public ProductsRepositorySimpleProcessorImpl(
            ProductsRepositoryProducer prodsRepoProducer,
            ProductProducer productProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        this.prodsRepoProducer = prodsRepoProducer;
        this.productProducer = productProducer;
        this.sqlQueryCreator = sqlQueryCreator;
    }

    @Override
    public ProductsRepository apply(Object nothing) {
        return prodsRepoProducer.getProductsRepositorySimpleInstance(
                productProducer,
                sqlQueryCreator);
    }
}
