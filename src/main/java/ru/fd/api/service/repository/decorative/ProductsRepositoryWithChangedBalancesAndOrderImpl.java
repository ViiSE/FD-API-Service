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

package ru.fd.api.service.repository.decorative;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.repository.ProductsRepositoryDecorative;

@Repository("productsRepositoryWithChangedBalancesAndOrder")
public class ProductsRepositoryWithChangedBalancesAndOrderImpl implements ProductsRepositoryDecorative<Order> {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Products> rmBalances;
    private final SQLQueryCreator<String, String> sqlQueryCreator;

    public ProductsRepositoryWithChangedBalancesAndOrderImpl(
            JdbcTemplate jdbcTemplate,
            @Qualifier("rmProductsChangedBalances") RowMapper<Products> rmBalances,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        this.jdbcTemplate = jdbcTemplate;
        this.rmBalances = rmBalances;
        this.sqlQueryCreator = sqlQueryCreator;
    }

    @Override
    public Products read(Order order) throws RepositoryException {
        try {
            return jdbcTemplate.queryForObject(
                    sqlQueryCreator.create("order_products_with_changed_balances.sql").content(),
                    new Object[] {order.id()},
                    rmBalances);
        } catch (CreatorException | DataAccessException ex) {
            throw new RepositoryException(ex.getMessage(), ex);
        }
    }
}
