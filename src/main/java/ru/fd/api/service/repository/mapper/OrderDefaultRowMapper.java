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
