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
import org.springframework.stereotype.Component;
import ru.fd.api.service.entity.Price;
import ru.fd.api.service.entity.Prices;
import ru.fd.api.service.producer.entity.PriceProducer;
import ru.fd.api.service.producer.entity.PricesProducer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("rmProductsWithPrices")
public class RmProductsWithPricesImpl implements RowMapper<Map<String, Prices>> {

    private final PriceProducer priceProducer;
    private final PricesProducer pricesProducer;

    public RmProductsWithPricesImpl(PriceProducer priceProducer, PricesProducer pricesProducer) {
        this.priceProducer = priceProducer;
        this.pricesProducer = pricesProducer;
    }

    @Override
    public Map<String, Prices> mapRow(ResultSet rs, int i) throws SQLException {
        Map<String, Prices> pricesMap = new HashMap<>();
        String id = "";
        List<Price> prices = new ArrayList<>();
        do {
            if(id.isEmpty())
                id = rs.getString("GID_TOVAR").trim();
            if(!id.equals(rs.getString("GID_TOVAR"))) {
                pricesMap.put(id, pricesProducer.getPricesInstance(new ArrayList<>(prices)));
                id = rs.getString("GID_TOVAR").trim();
                prices.clear();
            }

            String department_id = rs.getString("GID_DEP").trim();
            float value = rs.getInt("PRICE");
            prices.add(priceProducer.getPriceInstance(department_id, value));
        } while(rs.next());

        pricesMap.put(id, pricesProducer.getPricesInstance(new ArrayList<>(prices)));

        return pricesMap;
    }

    //        List<Price> prices = new ArrayList<>();
//        do {
//            String department_id = rs.getString("GID_DEP").trim();
//            float value = rs.getFloat("PRICE");
//            prices.add(priceProducer.getPriceDefaultInstance(department_id, value));
//        } while(rs.next());
//
//        return productProducer.getProductWithPricesInstance(product, pricesProducer.getPricesDefaultInstance(prices));

}
