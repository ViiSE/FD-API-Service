package ru.fd.api.service.repository.decorative;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Product;
import ru.fd.api.service.entity.ProductStatusesImpl;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.entity.Statuses;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.ProductsRepositoryDecorative;

import java.util.ArrayList;
import java.util.Map;

@Repository("productsRepositoryWithStatuses")
public class ProductsRepositoryWithStatusesImpl implements ProductsRepositoryDecorative {

    private final JdbcTemplate jdbcTemplate;
    private final ProductProducer productProducer;
    private final SQLQueryCreator<String, String> sqlQueryCreator;
    private final RowMapper<Map<String, Statuses>> rmProducts;

    public ProductsRepositoryWithStatusesImpl(
            JdbcTemplate jdbcTemplate,
            ProductProducer productProducer,
            SQLQueryCreator<String, String> sqlQueryCreator,
            RowMapper<Map<String, Statuses>> rmProducts) {
        this.jdbcTemplate = jdbcTemplate;
        this.productProducer = productProducer;
        this.sqlQueryCreator = sqlQueryCreator;
        this.rmProducts = rmProducts;
    }

    @Override
    public Products read(Products products) throws RepositoryException {
        try {
            Map<String, Statuses> statusesForProducts = jdbcTemplate.queryForObject(
                    sqlQueryCreator.create("products_with_statuses.sql").content(),
                    rmProducts);
            if(statusesForProducts != null)
                for(Product product: products) {
                    products.decorateProduct(
                            product.key(),
                            productProducer.getProductWithStatusesInstance(
                                    product,
                                    statusesForProducts.getOrDefault(
                                            product.id(),
                                            new ProductStatusesImpl(new ArrayList<>()))));
                }

            return products;
        } catch (CreatorException ex) {
            throw new RepositoryException(ex.getMessage(), ex.getCause());
        }
    }
}
