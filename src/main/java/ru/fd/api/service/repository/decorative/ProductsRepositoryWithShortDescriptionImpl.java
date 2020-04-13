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

@Repository("productsRepositoryWithShortDescription")
public class ProductsRepositoryWithShortDescriptionImpl implements ProductsRepositoryDecorative {

    private final JdbcTemplate jdbcTemplate;
    private final ProductProducer productProducer;
    private final SQLQueryCreator<String, String> sqlQueryCreator;
    private final RowMapper<Map<String, String>> rmProducts;

    public ProductsRepositoryWithShortDescriptionImpl(
            JdbcTemplate jdbcTemplate,
            ProductProducer productProducer,
            SQLQueryCreator<String, String> sqlQueryCreator,
            @Qualifier("productsWithShortDescriptionRowMapper") RowMapper<Map<String, String>> rmProducts) {
        this.jdbcTemplate = jdbcTemplate;
        this.productProducer = productProducer;
        this.sqlQueryCreator = sqlQueryCreator;
        this.rmProducts = rmProducts;
    }

    @Override
    public Products read(Products products) throws RepositoryException {
        try {
            Map<String, String> shortDescForProducts = jdbcTemplate.queryForObject(
                    sqlQueryCreator.create("products_with_short_description.sql").content(),
                    rmProducts);
            if (shortDescForProducts != null) {
                shortDescForProducts.forEach((id, shortDesc) -> {
                    Product product = products.findProductById(id);
                    if (product != null)
                        products.decorateProduct(id, productProducer.getProductWithShortDescriptionInstance(product, shortDesc));
                });
            }
            return products;
        } catch (CreatorException ex) {
            throw new RepositoryException(ex.getMessage(), ex.getCause());
        }
    }
}
