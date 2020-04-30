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
import ru.fd.api.service.entity.ProductWithCodeAvardaImpl;
import ru.fd.api.service.entity.ProductWithCountryImpl;
import ru.fd.api.service.producer.entity.ProductProducer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RmProductsSimpleImpl implements RowMapper<Product> {

    private final ProductProducer productProducer;

    public RmProductsSimpleImpl(ProductProducer productProducer) {
        this.productProducer = productProducer;
    }

    @Override
    public Product mapRow(ResultSet rs, int i) throws SQLException {
        String id = rs.getString("GIDTovar").trim();
        String categoryId = rs.getString("idCategory").trim();
        String vendorId = rs.getString("GIDMaker").trim();
        String countryId = rs.getString("GIDCountry");
        String unitId = rs.getString("OKEI").trim();
        String name = rs.getString("tName").trim().replaceFirst("\\. ", "");
        short tax = rs.getShort("TAX");
        String articul = "";
        String code = rs.getString("tIdent").trim();
        String codeAvarda = rs.getString("codeAvarda").trim();

        return new ProductWithCodeAvardaImpl(
                new ProductWithCountryImpl(
                        productProducer.getProductInstance(
                                id,
                            categoryId,
                            vendorId,
                            unitId,
                            name,
                            tax,
                            articul,
                            code),
                    codeAvarda),
                countryId);
    }
}
