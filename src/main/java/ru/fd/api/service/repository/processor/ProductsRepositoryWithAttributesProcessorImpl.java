package ru.fd.api.service.repository.processor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.repository.ProductsRepositoryProducer;
import ru.fd.api.service.repository.ProductsRepository;

@Component("productsRepositoryWithAttributesProcessor")
@Scope("prototype")
public class ProductsRepositoryWithAttributesProcessorImpl implements ProductsRepositoryProcessor {

    private final ProductsRepositoryProducer prodsRepoProducer;
    private final ProductProducer prodProducer;
    private final SQLQueryCreator<String, String> sqlQueryCreator;

    public ProductsRepositoryWithAttributesProcessorImpl(
            ProductsRepositoryProducer prodsRepoProducer,
            ProductProducer prodProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        this.prodsRepoProducer = prodsRepoProducer;
        this.prodProducer = prodProducer;
        this.sqlQueryCreator = sqlQueryCreator;
    }

    @Override
    public ProductsRepository apply(ProductsRepository productsRepository) {
        return prodsRepoProducer.getProductsRepositoryWithAttributesInstance(
                productsRepository,
                prodProducer,
                sqlQueryCreator);
    }
}
