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

import org.springframework.jdbc.core.RowMapper;
import ru.fd.api.service.entity.Product;
import ru.fd.api.service.producer.entity.PriceProducer;
import ru.fd.api.service.producer.entity.ProductProducer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RmProductsOfferImpl implements RowMapper<Product> {

    private final ProductProducer prProd;
    private final PriceProducer pcProd;

    public RmProductsOfferImpl(ProductProducer prProd, PriceProducer pcProd) {
        this.prProd = prProd;
        this.pcProd = pcProd;
    }

    @Override
    public Product mapRow(ResultSet rs, int i) throws SQLException {
        long offerId = rs.getLong("offerId");
        String id = rs.getString("GID").trim();
        float priceOriginal = rs.getFloat("priceOriginal");
        float priceOffer = rs.getFloat("priceOffer");

        return prProd.getProductOfferWithOfferIdInstance(
                prProd.getProductOfferWithOfferPriceInstance(
                        prProd.getProductOfferWithIdInstance(id),
                        pcProd.getPriceOfferInstance(priceOriginal, priceOffer)
                ),
                offerId);
    }
}
