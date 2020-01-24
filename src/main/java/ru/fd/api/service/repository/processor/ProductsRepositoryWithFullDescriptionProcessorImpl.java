package ru.fd.api.service.repository.processor;

import org.springframework.stereotype.Component;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.repository.ProductsRepositoryProducer;
import ru.fd.api.service.repository.ProductsRepository;

@Component("productsRepositoryWithFullDescriptionProcessor")
public class ProductsRepositoryWithFullDescriptionProcessorImpl implements ProductsRepositoryProcessor {

    private final ProductsRepositoryProducer prodsRepoProducer;
    private final ProductProducer prodProducer;
    private final SQLQueryCreator<String, String> sqlQueryCreator;

    public ProductsRepositoryWithFullDescriptionProcessorImpl(
            ProductsRepositoryProducer prodsRepoProducer,
            ProductProducer prodProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        this.prodsRepoProducer = prodsRepoProducer;
        this.prodProducer = prodProducer;
        this.sqlQueryCreator = sqlQueryCreator;
    }

    @Override
    public ProductsRepository apply(Object productsRepository) {
        return prodsRepoProducer.getProductsRepositoryWithFullDescriptionInstance(
                (ProductsRepository) productsRepository,
                prodProducer,
                sqlQueryCreator);
    }
}
