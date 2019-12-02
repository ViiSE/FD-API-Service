package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.repository.mapper.ProductsSimpleRowMapper;

@Repository("productsRepositorySimple")
public class ProductsRepositorySimpleImpl implements ProductsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Products readProducts() throws RepositoryException {
        try {
            return jdbcTemplate.queryForObject("SQL HERE", new ProductsSimpleRowMapper());
        } catch (DataAccessException ex) {
            throw new RepositoryException(ex.getMessage(), ex.getCause());
        }
    }
}
