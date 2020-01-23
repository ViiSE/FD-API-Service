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
import ru.fd.api.service.producer.entity.OrderProducer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersListChangedBalancesRowMapper implements RowMapper<List<Order>> {

    private final OrderProducer orderProducer;

    public OrdersListChangedBalancesRowMapper(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @Override
    public List<Order> mapRow(ResultSet rs, int i) throws SQLException {
        List<Order> orders = new ArrayList<>();
        do {
            long id = rs.getLong("ID_SITE");
            short status = rs.getShort("STATUS");
            orders.add(orderProducer.getOrderSimpleInstance(id, status));
        } while(rs.next());
        return orders;
    }
}
