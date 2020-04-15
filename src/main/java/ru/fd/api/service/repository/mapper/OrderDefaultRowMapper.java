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
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.entity.Orders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// TODO: 21.01.2020 CREATE THIS
public class OrderDefaultRowMapper implements RowMapper<Orders> {

//    private final OrderReaderService orderReaderService;

//    public OrderDefaultRowMapper(OrderReaderService orderReaderService) {
//        this.orderReaderService = orderReaderService;
//    }

    @Override
    public Orders mapRow(ResultSet rs, int i) throws SQLException {
        List<Order> orders = new ArrayList<>();

//        do {
//            long id = rs.getLong("ID_SITE");
//
//             customer
//            String cityId = rs.getString("ORDER_CITY_GID").trim();
//            String phoneNumber = rs.getString("CUST_PN").trim();
//            String email = rs.getString("CUST_EMAIL").trim();
//
//            short custType = rs.getShort("CUST_TYPE");
//            if(custType == )

//            orderProducer.getOrderSimpleInstance()
//            int quantity = rs.getInt("Kol_res");
//            products.add(productProducer.getOrderProductSimpleInstance(GID, quantity));
//        } while (rs.next());
//
//        return orderProductsProducer.getOrderProductsDefaultInstance(products);
        return null;
    }
}
