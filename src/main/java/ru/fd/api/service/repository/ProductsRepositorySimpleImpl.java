package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.mapper.ProductsSimpleRowMapper;

@Repository("productsRepositorySimple")
public class ProductsRepositorySimpleImpl implements ProductsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final ProductProducer productProducer;
    private final SQLQueryCreator<String, String> sqlQueryCreator;

    public ProductsRepositorySimpleImpl(
            ProductProducer productProducer, SQLQueryCreator<String, String> sqlQueryCreator) {
        this.productProducer = productProducer;
        this.sqlQueryCreator = sqlQueryCreator;
    }

    @Override
    public Products readProducts() throws RepositoryException {
        try {
            return jdbcTemplate.queryForObject(
                    sqlQueryCreator.create("products_simple.sql").content(),
                    new ProductsSimpleRowMapper(productProducer));
        } catch (DataAccessException | CreatorException ex) {
            throw new RepositoryException(ex.getMessage(), ex.getCause());
        }
    }
}
