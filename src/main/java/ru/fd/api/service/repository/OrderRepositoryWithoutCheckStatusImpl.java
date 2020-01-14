/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.constant.OrderResponses;
import ru.fd.api.service.data.OrderPojo;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.entity.OrderResponse;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.OrderResponseProducer;

import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

@Repository("orderRepositoryWithoutCheckStatus")
@Scope("prototype")
public class OrderRepositoryWithoutCheckStatusImpl implements OrderRepository<OrderResponse, Void> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final Order order;
    private final SQLQueryCreator<String, String> sqlQueryCreator;
    private final OrderResponseProducer orderResponseProducer;

    public OrderRepositoryWithoutCheckStatusImpl(
            Order order,
            SQLQueryCreator<String, String> sqlQueryCreator,
            OrderResponseProducer orderResponseProducer) {
        this.order = order;
        this.sqlQueryCreator = sqlQueryCreator;
        this.orderResponseProducer = orderResponseProducer;
    }

    @Override
    public OrderResponse insert() throws RepositoryException {
        try {
            OrderPojo orderPojo = (OrderPojo) order.formForSend();
            jdbcTemplate.update(
                    sqlQueryCreator.create("order_create.sql").content(),
                    orderPojo.getId(),
                    orderPojo.getCityId().getBytes("windows-1251"),
                    orderPojo.getCustomer().getPhoneNumber().getBytes("windows-1251"),
                    orderPojo.getCustomer().getEmail().getBytes("windows-1251"),
                    orderPojo.getCustomer().getInn().getBytes("windows-1251"),
                    orderPojo.getCustomer().getKpp().getBytes("windows-1251"),
                    orderPojo.getCustomer().getType(),
                    orderPojo.getDelivery().getCityId().getBytes("windows-1251"),
                    orderPojo.getDelivery().getType(),
                    orderPojo.getDelivery().getAddress().getBytes("windows-1251"),
                    orderPojo.getDelivery().getDepartmentId().getBytes("windows-1251"),
                    orderPojo.getDelivery().getDeliveryTimeId(),
                    orderPojo.getDelivery().getDeliveryDate(),
                    orderPojo.getPayTypeId(),
                    orderPojo.getDateTime(),
                    orderPojo.getComment().getBytes("windows-1251"));

            long orderPK = Long.parseLong(
                    Objects.requireNonNull(jdbcTemplate.queryForObject(
                            sqlQueryCreator.create("order_pk.sql").content(),
                            new Object[]{orderPojo.getId()}, String.class)));
            jdbcTemplate.batchUpdate(
                    sqlQueryCreator.create("order_products_add.sql").content(),
                    new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                            ps.setString(1, orderPojo.getProducts().get(i).getId());
                            ps.setInt(2, orderPojo.getProducts().get(i).getQuantity());
                            ps.setShort(3, (short) 0);
                            ps.setLong(4, orderPK);
                        }

                        @Override
                        public int getBatchSize() {
                            return orderPojo.getProducts().size();
                        }
                    });

            return orderResponseProducer.getOrderResponseWithMessageInstance(
                    orderResponseProducer.getOrderResponseSimpleInstance(orderPojo.getId(), OrderResponses.NOT_PROCESSED),
                    "OK");

        } catch (CreatorException | DataAccessException ex) {
            String exMsg = ex.getMessage();
            String msg = "SQL Exception";
            if(exMsg.contains("ISC error code:335544349")) {
                msg = exMsg.substring(exMsg.indexOf("Problematic key value is"));
                msg = msg.substring(0, msg.indexOf(";")) + " : must be unique!";
            }

            throw new RepositoryException(msg, ex.getMessage());
        } catch (UnsupportedEncodingException ex) {
            throw new RepositoryException("Unknown encoding");
        }
    }

    @Override
    public Void read(long orderPK) throws RepositoryException {
        throw new RepositoryException("Cannot read in OrderRepositoryWithoutCheckStatus instance");
    }
}
