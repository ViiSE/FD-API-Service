/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
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

public class ProductsChangedBalancesRowMapper implements RowMapper<Products> {

    private final ProductProducer productProducer;
    private final ProductsProducer productsProducer;
    private final BalanceProducer balanceProducer;
    private final BalancesProducer balancesProducer;

    public ProductsChangedBalancesRowMapper(
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
    public Products mapRow(ResultSet rs, int i) throws SQLException {
        List<Product> products = new ArrayList<>();
        List<Balance> balances = new ArrayList<>();
        String mainId = "";
        do {
            String id = rs.getString("GID_TOVAR").trim();

            if(mainId.isEmpty()) {
                mainId = id;
            } else if(!mainId.equals(id)) {
                products.add(productProducer
                        .getProductWithChangedBalancesInstance(
                                mainId,
                                balancesProducer.getBalancesDefaultInstance(balances)));
                mainId = id;
                balances.clear();
            }

            String departmentId = rs.getString("GID_DEP").trim();
            int quantity = rs.getInt("QUANTITY");

            Balance balance = balanceProducer.getBalanceDefaultInstance(departmentId, quantity);
            balances.add(balance);
        } while (rs.next());

        return productsProducer.getProductsDefaultInstance(productProducer, products);
    }
}
