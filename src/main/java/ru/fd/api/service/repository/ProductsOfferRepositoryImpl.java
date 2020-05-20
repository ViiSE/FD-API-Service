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
import ru.fd.api.service.entity.Product;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.entity.ProductsOfferImpl;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.PriceProducer;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.mapper.RmProductsOfferImpl;

import java.util.List;

@Repository("productsOfferRepository")
public class ProductsOfferRepositoryImpl implements ProductsRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ProductProducer productProducer;
    private final PriceProducer priceProducer;
    private final SQLQueryCreator<String, String> sqlQueryCreator;

    public ProductsOfferRepositoryImpl(
            JdbcTemplate jdbcTemplate,
            ProductProducer productProducer,
            PriceProducer priceProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        this.jdbcTemplate = jdbcTemplate;
        this.productProducer = productProducer;
        this.priceProducer = priceProducer;
        this.sqlQueryCreator = sqlQueryCreator;
    }

    @Override
    public Products read() throws RepositoryException {
        try {
            List<Product> products = jdbcTemplate.query(
                    sqlQueryCreator.create("products_offer.sql").content(),
                    new RmProductsOfferImpl(productProducer, priceProducer));
            return new ProductsOfferImpl(products);
        } catch (DataAccessException | CreatorException ex) {
            throw new RepositoryException(ex.getMessage(), ex);
        }
    }
}
