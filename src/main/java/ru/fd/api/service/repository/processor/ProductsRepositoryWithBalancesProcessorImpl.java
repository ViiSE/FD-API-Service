package ru.fd.api.service.repository.processor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.producer.entity.BalanceProducer;
import ru.fd.api.service.producer.entity.BalancesProducer;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.repository.ProductsRepositoryProducer;
import ru.fd.api.service.repository.ProductsRepository;

@Component("productsRepositoryWithBalancesProcessor")
@Scope("prototype")
public class ProductsRepositoryWithBalancesProcessorImpl implements ProductsRepositoryProcessor {

    private final ProductsRepositoryProducer prodsRepoProducer;
    private final ProductProducer prodProducer;
    private final BalanceProducer balanceProducer;
    private final BalancesProducer balancesProducer;
    private final SQLQueryCreator<String, String> sqlQueryCreator;

    public ProductsRepositoryWithBalancesProcessorImpl(
            ProductsRepositoryProducer prodsRepoProducer,
            ProductProducer prodProducer,
            BalanceProducer balanceProducer,
            BalancesProducer balancesProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        this.prodsRepoProducer = prodsRepoProducer;
        this.prodProducer = prodProducer;
        this.balanceProducer = balanceProducer;
        this.balancesProducer = balancesProducer;
        this.sqlQueryCreator = sqlQueryCreator;
    }

    @Override
    public ProductsRepository apply(Object productsRepository) {
        return prodsRepoProducer.getProductsRepositoryWithBalancesInstance(
                (ProductsRepository) productsRepository,
                prodProducer,
                balanceProducer,
                balancesProducer,
                sqlQueryCreator);
    }
}
