package ru.fd.api.service.repository.processor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.repository.ProductsRepositoryProducer;
import ru.fd.api.service.repository.ProductsRepository;

@Component("productsRepositoryWithShortDescriptionProcessor")
@Scope("prototype")
public class ProductsRepositoryWithShortDescriptionProcessorImpl implements ProductsRepositoryProcessor {

    private final ProductsRepositoryProducer prodsRepoProducer;
    private final ProductProducer prodProducer;
    private final SQLQueryCreator<String, String> sqlQueryCreator;

    public ProductsRepositoryWithShortDescriptionProcessorImpl(
            ProductsRepositoryProducer prodsRepoProducer,
            ProductProducer prodProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        this.prodsRepoProducer = prodsRepoProducer;
        this.prodProducer = prodProducer;
        this.sqlQueryCreator = sqlQueryCreator;
    }

    @Override
    public ProductsRepository apply(Object productsRepository) {
        return prodsRepoProducer.getProductsRepositoryWithShortDescriptionInstance(
                (ProductsRepository) productsRepository,
                prodProducer,
                sqlQueryCreator);
    }
}
