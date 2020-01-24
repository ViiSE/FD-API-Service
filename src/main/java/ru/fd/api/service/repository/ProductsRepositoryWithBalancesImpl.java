package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Balances;
import ru.fd.api.service.entity.Product;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.BalanceProducer;
import ru.fd.api.service.producer.entity.BalancesProducer;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.mapper.ProductsWithBalancesRowMapper;

import java.util.ArrayList;
import java.util.Map;

@Repository("productsRepositoryWithBalances")
@Scope("prototype")
public class ProductsRepositoryWithBalancesImpl implements ProductsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final ProductsRepository productsRepository;
    private final ProductProducer productProducer;
    private final BalanceProducer balanceProducer;
    private final BalancesProducer balancesProducer;
    private final SQLQueryCreator<String, String> sqlQueryCreator;

    public ProductsRepositoryWithBalancesImpl(
            ProductsRepository productsRepository,
            ProductProducer productProducer,
            BalanceProducer balanceProducer,
            BalancesProducer balancesProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        this.productsRepository = productsRepository;
        this.productProducer = productProducer;
        this.balanceProducer = balanceProducer;
        this.balancesProducer = balancesProducer;
        this.sqlQueryCreator = sqlQueryCreator;
    }

    @Override
    public Products read() throws RepositoryException {
        try {
            Products products = productsRepository.read();
            Map<String, Balances> balanceForProducts = jdbcTemplate.queryForObject(
                    sqlQueryCreator.create("products_with_balances.sql").content(),
                    new ProductsWithBalancesRowMapper(balanceProducer, balancesProducer));
            if(balanceForProducts != null)
                    for(Product product : products) {
                        products.decorateProduct(
                                product.key(),
                                productProducer.getProductWithBalancesInstance(
                                        product,
                                        balanceForProducts.getOrDefault(
                                                product.id(),
                                                balancesProducer.getBalancesDefaultInstance(new ArrayList<>()))));

                    }

            return products;
        } catch (CreatorException ex) {
            throw new RepositoryException(ex.getMessage(), ex.getCause());
        }
    }
}
