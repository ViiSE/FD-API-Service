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
import org.springframework.stereotype.Component;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.producer.entity.OrderProducer;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component("ordersListChangedBalancesRowMapper")
public class OrdersListChangedBalancesRowMapper implements RowMapper<Order> {

    private final OrderProducer orderProducer;

    public OrdersListChangedBalancesRowMapper(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @Override
    public Order mapRow(ResultSet rs, int i) throws SQLException {
        long id = rs.getLong("ID_SITE");
        short status = rs.getShort("STATUS");
        return orderProducer.getOrderSimpleInstance(id, status);
    }
}
