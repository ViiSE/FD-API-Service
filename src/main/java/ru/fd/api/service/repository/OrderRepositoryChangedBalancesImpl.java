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
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.OrderProducer;
import ru.fd.api.service.repository.mapper.OrderChangedBalancesRowMapper;
import ru.fd.api.service.repository.mapper.OrdersListChangedBalancesRowMapper;

import java.util.List;

@Repository("orderRepositoryChangedBalances")
public class OrderRepositoryChangedBalancesImpl implements OrderRepository<Void, Order> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final SQLQueryCreator<String, String> sqlQueryCreator;
    private final OrderProducer orderProducer;

    public OrderRepositoryChangedBalancesImpl(SQLQueryCreator<String, String> sqlQueryCreator, OrderProducer orderProducer) {
        this.sqlQueryCreator = sqlQueryCreator;
        this.orderProducer = orderProducer;
    }

    @Override
    public Void insert() throws RepositoryException {
        throw new RepositoryException("Cannot insert in ChangedBalances instance");
    }

    @Override
    public Order read(long id) throws RepositoryException {
        try {
            return jdbcTemplate.queryForObject(
                    sqlQueryCreator.create("order_status.sql").content(),
                    new Object[] {id},
                    new OrderChangedBalancesRowMapper(orderProducer));
        } catch (DataAccessException | CreatorException ex) {
            throw new RepositoryException(ex.getMessage());
        }
    }

    @Override
    public List<Order> readAll() throws RepositoryException {
        try {
            return jdbcTemplate.queryForObject(
                    sqlQueryCreator.create("orders_statuses.sql").content(),
                    new OrdersListChangedBalancesRowMapper(orderProducer));
        } catch (DataAccessException | CreatorException ex) {
            throw new RepositoryException(ex.getMessage());
        }
    }

    @Override
    public List<Order> readFirst(int sliceSize) throws RepositoryException {
        try {
            String source = sqlQueryCreator.create("orders_statuses_first.sql").content();
            return jdbcTemplate.queryForObject(
                    source.replaceAll("#SLICE_SIZE#", String.valueOf(sliceSize)),
                    new OrdersListChangedBalancesRowMapper(orderProducer));
        } catch (DataAccessException | CreatorException ex) {
            throw new RepositoryException(ex.getMessage());
        }
    }
}
