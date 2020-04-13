package ru.fd.api.service.repository.decorative;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Product;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.ProductsRepositoryDecorative;

import java.util.Map;

@Repository("productsRepositoryWithFullDescription")
public class ProductsRepositoryWithFullDescriptionImpl implements ProductsRepositoryDecorative {

    private final JdbcTemplate jdbcTemplate;
    private final ProductProducer productProducer;
    private final SQLQueryCreator<String, String> sqlQueryCreator;
    private final RowMapper<Map<String, String>> rmProducts;

    public ProductsRepositoryWithFullDescriptionImpl(
            JdbcTemplate jdbcTemplate,
            ProductProducer productProducer,
            SQLQueryCreator<String, String> sqlQueryCreator,
            @Qualifier("productsWithFullDescriptionRowMapper") RowMapper<Map<String, String>> rmProducts) {
        this.jdbcTemplate = jdbcTemplate;
        this.productProducer = productProducer;
        this.sqlQueryCreator = sqlQueryCreator;
        this.rmProducts = rmProducts;
    }

    @Override
    public Products read(Products products) throws RepositoryException {
        try {
            Map<String, String> fullDescForProducts = jdbcTemplate.queryForObject(
                    sqlQueryCreator.create("products_with_full_description.sql").content(),
                    rmProducts);
            if (fullDescForProducts != null) {
                fullDescForProducts.forEach((id, fullDesc) -> {
                    Product product = products.findProductById(id);
                    if (product != null)
                        products.decorateProduct(id, productProducer.getProductWithFullDescriptionInstance(product, fullDesc));
                });
            }
            return products;
        } catch (CreatorException ex) {
            throw new RepositoryException(ex.getMessage(), ex.getCause());
        }
    }
}
