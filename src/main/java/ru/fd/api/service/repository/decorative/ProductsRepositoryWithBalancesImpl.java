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

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Balances;
import ru.fd.api.service.entity.Product;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.BalanceProducer;
import ru.fd.api.service.producer.entity.BalancesProducer;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.ProductsRepositoryDecorative;
import ru.fd.api.service.repository.mapper.RmProductsWithBalancesImpl;

import java.util.ArrayList;
import java.util.Map;

@Repository("productsRepositoryWithBalances")
public class ProductsRepositoryWithBalancesImpl implements ProductsRepositoryDecorative<Products> {

    private final JdbcTemplate jdbcTemplate;
    private final ProductProducer productProducer;
    private final SQLQueryCreator<String, String> sqlQueryCreator;
    private final BalanceProducer bProd;
    private final BalancesProducer bsProd;

    public ProductsRepositoryWithBalancesImpl(
            JdbcTemplate jdbcTemplate,
            ProductProducer productProducer,
            SQLQueryCreator<String, String> sqlQueryCreator,
            BalanceProducer bProd,
            BalancesProducer bsProd) {
        this.jdbcTemplate = jdbcTemplate;
        this.productProducer = productProducer;
        this.sqlQueryCreator = sqlQueryCreator;
        this.bProd = bProd;
        this.bsProd = bsProd;
    }

    @Override
    public Products read(Products products) throws RepositoryException {
        try {
            Map<String, Balances> balanceForProducts = jdbcTemplate.queryForObject(
                    sqlQueryCreator.create("products_with_balances.sql").content(),
                    new RmProductsWithBalancesImpl(
                            bProd,
                            bsProd));
            if(balanceForProducts != null)
                    for(Product product : products) {
                        products.decorateProduct(
                                product.key(),
                                productProducer.getProductWithBalancesInstance(
                                        product,
                                        balanceForProducts.getOrDefault(
                                                product.id(),
                                                bsProd.getBalancesInstance(new ArrayList<>()))));

                    }

            return products;
        } catch (CreatorException ex) {
            throw new RepositoryException(ex.getMessage(), ex);
        }
    }
}
