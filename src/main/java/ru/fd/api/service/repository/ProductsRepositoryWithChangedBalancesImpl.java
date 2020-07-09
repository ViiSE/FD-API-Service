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
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.BalanceProducer;
import ru.fd.api.service.producer.entity.BalancesProducer;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.entity.ProductsProducer;
import ru.fd.api.service.repository.mapper.RseProductsChangedBalancesImpl;

import java.time.ZonedDateTime;

@Repository("productsRepositoryWithChangedBalances")
public class ProductsRepositoryWithChangedBalancesImpl implements ProductsRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SQLQueryCreator<String, String> sqlQueryCreator;
    private final ProductProducer pProd;
    private final ProductsProducer psProd;
    private final BalanceProducer bProd;
    private final BalancesProducer bsProd;

    public ProductsRepositoryWithChangedBalancesImpl(
            JdbcTemplate jdbcTemplate,
            SQLQueryCreator<String, String> sqlQueryCreator,
            ProductProducer pProd,
            ProductsProducer psProd,
            BalanceProducer bProd,
            BalancesProducer bsProd) {
        this.jdbcTemplate = jdbcTemplate;
        this.sqlQueryCreator = sqlQueryCreator;
        this.pProd = pProd;
        this.psProd = psProd;
        this.bProd = bProd;
        this.bsProd = bsProd;
    }

    @Override
    public Products read() throws RepositoryException {
        try {
            Products products = jdbcTemplate.query(
                    sqlQueryCreator.create("products_with_changed_balances.sql").content(),
                    new RseProductsChangedBalancesImpl(
                            pProd,
                            psProd,
                            bProd,
                            bsProd));

            jdbcTemplate.batchUpdate(
                    sqlQueryCreator.create("update_request_date_time_changed_balances.sql").content());

            return psProd.getProductsChangedBalancesWithRequestDateTimeInstance(products, ZonedDateTime.now());
        } catch (CreatorException | DataAccessException ex) {
            throw new RepositoryException(ex.getMessage(), ex);
        }
    }
}
