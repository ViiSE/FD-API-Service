/*
 * Copyright 2019 ViiSE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.fd.api.service.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.OrderProducer;
import ru.fd.api.service.repository.mapper.RmOrderChangedBalancesImpl;
import ru.fd.api.service.repository.mapper.RmOrdersListChangedBalancesImpl;

import java.util.List;

@Repository("orderRepositoryChangedBalances")
public class OrderRepositoryChangedBalancesImpl implements OrderRepository<Void, Order> {

    private final JdbcTemplate jdbcTemplate;
    private final SQLQueryCreator<String, String> sqlQueryCreator;
    private final OrderProducer oProd;

    public OrderRepositoryChangedBalancesImpl(
            JdbcTemplate jdbcTemplate,
            SQLQueryCreator<String, String> sqlQueryCreator,
            OrderProducer oProd) {
        this.jdbcTemplate = jdbcTemplate;
        this.sqlQueryCreator = sqlQueryCreator;
        this.oProd = oProd;
    }

    @Override
    public Void insert(Order order) throws RepositoryException {
        throw new RepositoryException("Cannot insert in ChangedBalances instance");
    }

    @Override
    public Order read(long id) throws RepositoryException {
        try {
            return jdbcTemplate.queryForObject(
                    sqlQueryCreator.create("order_status.sql").content(),
                    new Object[] {id},
                    new RmOrderChangedBalancesImpl(
                            oProd));
        } catch (DataAccessException | CreatorException ex) {
            throw new RepositoryException(ex.getMessage());
        }
    }

    @Override
    public List<Order> readAll() throws RepositoryException {
        try {
            return jdbcTemplate.query(
                    sqlQueryCreator.create("orders_statuses.sql").content(),
                    new RmOrdersListChangedBalancesImpl(oProd));
        } catch (DataAccessException | CreatorException ex) {
            throw new RepositoryException(ex.getMessage());
        }
    }

    @Override
    public List<Order> readFirst(int sliceSize) throws RepositoryException {
        try {
            String source = sqlQueryCreator.create("orders_statuses_first.sql").content();
            return jdbcTemplate.query(
                    source.replaceAll("#SLICE_SIZE#", String.valueOf(sliceSize)),
                    new RmOrdersListChangedBalancesImpl(oProd));
        } catch (DataAccessException | CreatorException ex) {
            throw new RepositoryException(ex.getMessage());
        }
    }
}
