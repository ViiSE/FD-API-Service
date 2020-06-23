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
import ru.fd.api.service.data.DescriptionPojo;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Description;
import ru.fd.api.service.entity.Product;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.DescriptionProducer;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.ProductsRepositoryDecorative;
import ru.fd.api.service.repository.mapper.RseProductsWithDescriptionImpl;

import java.util.Map;

@Repository("productsRepositoryWithDescription")
public class ProductsRepositoryWithDescriptionImpl implements ProductsRepositoryDecorative<Products> {

    private final JdbcTemplate jdbcTemplate;
    private final ProductProducer productProducer;
    private final DescriptionProducer descriptionProducer;
    private final SQLQueryCreator<String, String> sqlQueryCreator;

    public ProductsRepositoryWithDescriptionImpl(
            JdbcTemplate jdbcTemplate,
            ProductProducer productProducer,
            DescriptionProducer descriptionProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        this.jdbcTemplate = jdbcTemplate;
        this.productProducer = productProducer;
        this.descriptionProducer = descriptionProducer;
        this.sqlQueryCreator = sqlQueryCreator;
    }

    @Override
    public Products read(Products products) throws RepositoryException {
        try {
            Map<String, Description> descMap = jdbcTemplate.query(
                    sqlQueryCreator.create("products_with_description.sql").content(),
                    new RseProductsWithDescriptionImpl(descriptionProducer));

            if(descMap != null) {
                for (Product product : products) {
                    DescriptionPojo descPojo = (DescriptionPojo) descMap
                            .getOrDefault(
                                    product.id(),
                                    descriptionProducer.getDescriptionFullInstance(
                                            descriptionProducer.getDescriptionShortInstance(""),
                                            ""))
                            .formForSend();

                    products.decorateProduct(
                            product.key(),
                            productProducer.getProductWithFullDescriptionInstance(
                                    productProducer.getProductWithShortDescriptionInstance(
                                            product,
                                            descPojo.getShortDescription()),
                                    descPojo.getFullDescription()));
                }
            }

            return products;
        } catch (CreatorException ex) {
            throw new RepositoryException(ex.getMessage(), ex);
        }
    }
}
