/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.BalanceProducer;
import ru.fd.api.service.producer.entity.BalancesProducer;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.entity.ProductsProducer;
import ru.fd.api.service.repository.mapper.ProductsChangedBalancesRowMapper;

@Repository("productsRepositoryWithChangedBalances")
@Scope("prototype")
public class ProductsRepositoryWithChangedBalancesImpl implements ProductsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final ProductProducer productProducer;
    private final ProductsProducer productsProducer;
    private final BalanceProducer balanceProducer;
    private final BalancesProducer balancesProducer;
    private final SQLQueryCreator<String, String> sqlQueryCreator;

    public ProductsRepositoryWithChangedBalancesImpl(
            ProductProducer productProducer,
            ProductsProducer productsProducer,
            BalanceProducer balanceProducer,
            BalancesProducer balancesProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        this.productProducer = productProducer;
        this.productsProducer = productsProducer;
        this.balanceProducer = balanceProducer;
        this.balancesProducer = balancesProducer;
        this.sqlQueryCreator = sqlQueryCreator;
    }

    @Override
    public Products readProducts() throws RepositoryException {
        try {
            return jdbcTemplate.queryForObject(
                    sqlQueryCreator.create("SQL_HERE").content(),
                    new ProductsChangedBalancesRowMapper(
                            productProducer,
                            productsProducer,
                            balanceProducer,
                            balancesProducer));
        } catch (CreatorException | DataAccessException ex) {
            throw new RepositoryException(ex.getMessage(), ex.getCause());
        }
    }
}
