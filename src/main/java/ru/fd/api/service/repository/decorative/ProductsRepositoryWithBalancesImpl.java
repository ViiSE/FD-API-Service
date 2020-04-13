package ru.fd.api.service.repository.decorative;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Balances;
import ru.fd.api.service.entity.Product;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.BalancesProducer;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.ProductsRepositoryDecorative;

import java.util.ArrayList;
import java.util.Map;

@Repository("productsRepositoryWithBalances")
public class ProductsRepositoryWithBalancesImpl implements ProductsRepositoryDecorative {

    private final JdbcTemplate jdbcTemplate;
    private final ProductProducer productProducer;
    private final SQLQueryCreator<String, String> sqlQueryCreator;
    private final BalancesProducer balancesProducer;
    private final RowMapper<Map<String, Balances>> rmProducts;

    public ProductsRepositoryWithBalancesImpl(
            JdbcTemplate jdbcTemplate,
            ProductProducer productProducer,
            RowMapper<Map<String, Balances>> rmProducts,
            SQLQueryCreator<String, String> sqlQueryCreator,
            BalancesProducer balancesProducer) {
        this.jdbcTemplate = jdbcTemplate;
        this.productProducer = productProducer;
        this.rmProducts = rmProducts;
        this.sqlQueryCreator = sqlQueryCreator;
        this.balancesProducer = balancesProducer;
    }

    @Override
    public Products read(Products products) throws RepositoryException {
        try {
            Map<String, Balances> balanceForProducts = jdbcTemplate.queryForObject(
                    sqlQueryCreator.create("products_with_balances.sql").content(),
                    rmProducts);
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
