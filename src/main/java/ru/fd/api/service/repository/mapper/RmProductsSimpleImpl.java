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

import org.springframework.jdbc.core.RowMapper;
import ru.fd.api.service.entity.Product;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.entity.ProductsImpl;
import ru.fd.api.service.producer.entity.ProductProducer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//@Component("rmProductsSimple")
public class RmProductsSimpleImpl implements RowMapper<Products> {

    private final ProductProducer productProducer;

    public RmProductsSimpleImpl(ProductProducer productProducer) {
        this.productProducer = productProducer;
    }

    @Override
    public Products mapRow(ResultSet rs, int i) throws SQLException {
        List<Product> products = new ArrayList<>();
        int key = 0;
        do {
            String id = rs.getString("GIDTovar").trim();
            String categoryId = rs.getString("idCategory").trim();
            String vendorId = rs.getString("GIDMaker").trim();
            String unitId = rs.getString("OKEI").trim();
            String name = rs.getString("tName").trim().replaceFirst("\\. ", "");
            short tax = rs.getShort("TAX");
            String articul = "";
            String code = rs.getString("tIdent").trim();

            products.add(productProducer.getProductSimpleInstance(
                    productProducer.getProductInstance(
                            id,
                            categoryId,
                            vendorId,
                            unitId,
                            name,
                            tax,
                            articul,
                            code),
                    key++));

        } while(rs.next());
        return new ProductsImpl(productProducer, products);
    }
}
