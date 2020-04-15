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

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Prices;
import ru.fd.api.service.entity.Product;
import ru.fd.api.service.entity.ProductWithPricesImpl;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.ProductsRepositoryDecorative;

import java.util.Map;

@Repository("productsRepositoryWithPrices")
public class ProductsRepositoryWithPricesImpl implements ProductsRepositoryDecorative<Products> {

    private final JdbcTemplate jdbcTemplate;
    private final ProductProducer productProducer;
    private final SQLQueryCreator<String, String> sqlQueryCreator;
    private final RowMapper<Map<String, Prices>> rmProducts;

    public ProductsRepositoryWithPricesImpl(
            JdbcTemplate jdbcTemplate,
            ProductProducer productProducer,
            SQLQueryCreator<String, String> sqlQueryCreator,
            RowMapper<Map<String, Prices>> rmProducts) {
        this.jdbcTemplate = jdbcTemplate;
        this.productProducer = productProducer;
        this.sqlQueryCreator = sqlQueryCreator;
        this.rmProducts = rmProducts;
    }

    @Override
    public Products read(Products products) throws RepositoryException {
          try {
              Map<String, Prices> pricesForProducts = jdbcTemplate.queryForObject(
                      sqlQueryCreator.create("products_with_prices.sql").content(),
                      rmProducts);

              if(pricesForProducts != null)
                  for(Product product: products) {
                      if(pricesForProducts.containsKey(product.id())) {
                          products.decorateProduct(
                                  product.key(),
                                  productProducer.getProductWithPricesInstance(
                                          product,
                                          pricesForProducts.get(product.id())));
                      }
                  }

              products.removeProducts(ProductWithPricesImpl.class);

              return products;
          } catch (DataAccessException | CreatorException ex) {
              throw new RepositoryException(ex.getMessage(), ex);
          }
    }
}