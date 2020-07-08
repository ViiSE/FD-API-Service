/*
 * Copyright 2020 ViiSE
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

import org.springframework.jdbc.core.ResultSetExtractor;
import ru.fd.api.service.entity.Price;
import ru.fd.api.service.entity.Product;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.producer.entity.PriceProducer;
import ru.fd.api.service.producer.entity.PricesProducer;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.entity.ProductsProducer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RseProductsOfferImpl implements ResultSetExtractor<Products> {

    private final ProductsProducer productsProd;
    private final ProductProducer productProd;
    private final PriceProducer priceProd;
    private final PricesProducer pricesProd;

    public RseProductsOfferImpl(
            ProductsProducer productsProd,
            ProductProducer productProd,
            PriceProducer priceProd,
            PricesProducer pricesProd) {
        this.productsProd = productsProd;
        this.productProd = productProd;
        this.priceProd = priceProd;
        this.pricesProd = pricesProd;
    }

    @Override
    public Products extractData(ResultSet rs) throws SQLException {
        List<Product> products = new ArrayList<>();
        List<Price> prices = new ArrayList<>();
        String mainId = "";
        long offerId = 0L;
        while (rs.next()) {
            String id = rs.getString("GID").trim();

            if(mainId.isEmpty()) {
                mainId = id;
                offerId = rs.getLong("offerId");
            } else if(!mainId.equals(id)) {
                products.add(
                        productProd.getProductOfferWithOfferIdInstance(
                                productProd.getProductOfferWithOfferPricesInstance(
                                        productProd.getProductOfferWithIdInstance(id),
                                        pricesProd.getPricesOfferInstance(new ArrayList<>(prices))
                                ),
                                offerId));
                mainId = id;
                offerId = rs.getLong("offerId");
                prices.clear();
            }

            String departmentId = rs.getString("GID_DEP").trim();
            float priceOriginal = rs.getFloat("priceOriginal");
            float priceOffer = rs.getFloat("priceOffer");


            Price price = priceProd.getPriceOfferWithDepartmentIdInstance(
                    priceProd.getPriceOfferInstance(
                            priceOriginal,
                            priceOffer),
                    departmentId);
            prices.add(price);
        }

        products.add(productProd.getProductOfferWithOfferIdInstance(
                productProd.getProductOfferWithOfferPricesInstance(
                        productProd.getProductOfferWithIdInstance(mainId),
                        pricesProd.getPricesOfferInstance(new ArrayList<>(prices))
                ),
                offerId));

        return productsProd.getProductsOfferInstance(products);
    }
}
