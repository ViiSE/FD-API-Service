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

package ru.fd.api.service.repository.mapper;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import ru.fd.api.service.entity.Balance;
import ru.fd.api.service.entity.Product;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.producer.entity.BalanceProducer;
import ru.fd.api.service.producer.entity.BalancesProducer;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.entity.ProductsProducer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RseProductsChangedBalancesImpl implements ResultSetExtractor<Products> {

    private final ProductProducer productProducer;
    private final ProductsProducer productsProducer;
    private final BalanceProducer balanceProducer;
    private final BalancesProducer balancesProducer;

    public RseProductsChangedBalancesImpl(
            ProductProducer productProducer,
            ProductsProducer productsProducer,
            BalanceProducer balanceProducer,
            BalancesProducer balancesProducer) {
        this.productProducer = productProducer;
        this.productsProducer = productsProducer;
        this.balanceProducer = balanceProducer;
        this.balancesProducer = balancesProducer;
    }

    @Override
    public Products extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Product> products = new ArrayList<>();
        List<Balance> balances = new ArrayList<>();
        String mainId = "";
        while (rs.next()) {
            String id = rs.getString("GID_TOVAR").trim();

            if(mainId.isEmpty()) {
                mainId = id;
            } else if(!mainId.equals(id)) {
                products.add(productProducer
                        .getProductWithChangedBalancesInstance(
                                mainId,
                                balancesProducer.getBalancesInstance(new ArrayList<>(balances))));
                mainId = id;
                balances.clear();
            }

            String departmentId = rs.getString("GID_DEP").trim();
            int quantity = rs.getInt("QUANTITY");

            Balance balance = balanceProducer.getBalanceInstance(departmentId, quantity);
            balances.add(balance);
        }

        products.add(productProducer
                .getProductWithChangedBalancesInstance(
                        mainId,
                        balancesProducer.getBalancesInstance(balances)));

        return productsProducer.getProductsInstance(productProducer, products);
    }
}
