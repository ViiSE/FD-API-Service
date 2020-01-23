/*
 *  Copyright 2019 ViiSE.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package ru.fd.api.service.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.fd.api.service.entity.Product;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.producer.entity.ProductsProducer;
import ru.fd.api.service.producer.entity.ProductProducer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderProductsLackRowMapper implements RowMapper<Products> {

    private final ProductProducer productProducer;
    private final ProductsProducer orderProductsProducer;

    public OrderProductsLackRowMapper(ProductProducer productProducer, ProductsProducer orderProductsProducer) {
        this.productProducer = productProducer;
        this.orderProductsProducer = orderProductsProducer;
    }

    @Override
    public Products mapRow(ResultSet rs, int i) throws SQLException {
        List<Product> products = new ArrayList<>();

        do {
            String GID = rs.getString("GID").trim();
            int quantity = rs.getInt("Kol_res");
            products.add(productProducer.getOrderProductSimpleInstance(GID, quantity));
        } while (rs.next());

        return orderProductsProducer.getOrderProductsDefaultInstance(products);
    }
}
