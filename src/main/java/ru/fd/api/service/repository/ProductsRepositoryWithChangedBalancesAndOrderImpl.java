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
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.entity.Product;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.BalanceProducer;
import ru.fd.api.service.producer.entity.BalancesProducer;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.entity.ProductsProducer;
import ru.fd.api.service.repository.mapper.OrderProductsGidsRowMapper;
import ru.fd.api.service.repository.mapper.OrderProductsLackRowMapper;
import ru.fd.api.service.repository.mapper.ProductsChangedBalancesRowMapper;

import java.util.List;

@Repository("productsRepositoryWithChangedBalancesAndOrder")
@Scope("prototype")
public class ProductsRepositoryWithChangedBalancesAndOrderImpl implements ProductsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final Order order;
    private final ProductProducer productProducer;
    private final ProductsProducer productsProducer;
    private final BalanceProducer balanceProducer;
    private final BalancesProducer balancesProducer;
    private final SQLQueryCreator<String, String> sqlQueryCreator;

    public ProductsRepositoryWithChangedBalancesAndOrderImpl(
            Order order,
            ProductProducer productProducer,
            ProductsProducer productsProducer,
            BalanceProducer balanceProducer,
            BalancesProducer balancesProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        this.order = order;
        this.productProducer = productProducer;
        this.productsProducer = productsProducer;
        this.balanceProducer = balanceProducer;
        this.balancesProducer = balancesProducer;
        this.sqlQueryCreator = sqlQueryCreator;
    }

    @Override
    public Products read() throws RepositoryException {
        try {
            List<String> gids = jdbcTemplate.query(
                    sqlQueryCreator.create("order_products_gid.sql").content(),
                    new Object[] {order.id()}, new OrderProductsGidsRowMapper());

            // TODO: 23.01.2020 WRITE SQL
            StringBuilder sqlB = new StringBuilder(sqlQueryCreator.create("SQL_HERE").content());

            sqlB.append(" WHERE CHAR_TO_UUID(t.GID) = ").append(gids.get(0));
            for(int i = 1; i < gids.size(); i++)
                sqlB.append(" AND CHAR_TO_UUID(t.GID) = ").append(gids.get(i));
            String sql = sqlB.append(" ORDER BY 2").toString();

            return jdbcTemplate.queryForObject(
                    sql,
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
