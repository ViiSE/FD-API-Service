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

package ru.fd.api.service.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.constant.OrderStatuses;
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
import java.util.List;
import java.util.Objects;

@Repository("orderRepository")
public class OrderRepositoryImpl implements OrderRepository<OrderResponse, Order> {

    private final JdbcTemplate jdbcTemplate;
    private final SQLQueryCreator<String, String> sqlQueryCreator;
    private final OrderResponseProducer orderResponseProducer;

    public OrderRepositoryImpl(
            JdbcTemplate jdbcTemplate,
            SQLQueryCreator<String, String> sqlQueryCreator,
            OrderResponseProducer orderResponseProducer) {
        this.jdbcTemplate = jdbcTemplate;
        this.sqlQueryCreator = sqlQueryCreator;
        this.orderResponseProducer = orderResponseProducer;
    }

    @Override
    public OrderResponse insert(Order order) throws RepositoryException {
        try {
            OrderPojo orderPojo = (OrderPojo) order.formForSend();

            byte[] depId = orderPojo.getDelivery().getDepartmentId() != null
                    ? orderPojo.getDelivery().getDepartmentId().getBytes("windows-1251")
                    : null;
            jdbcTemplate.update(
                    sqlQueryCreator.create("order_create.sql").content(),
                    orderPojo.getId(),
                    orderPojo.getCityId(),
                    orderPojo.getCustomer().getPhoneNumber().getBytes("windows-1251"),
                    orderPojo.getCustomer().getEmail().getBytes("windows-1251"),
                    orderPojo.getCustomer().getInn().getBytes("windows-1251"),
                    orderPojo.getCustomer().getKpp().getBytes("windows-1251"),
                    orderPojo.getCustomer().getType(),
                    orderPojo.getDelivery().getCityId(),
                    orderPojo.getDelivery().getType(),
                    orderPojo.getDelivery().getAddress().getBytes("windows-1251"),
                    depId,
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
                    orderResponseProducer.getOrderResponseSimpleInstance(orderPojo.getId(), OrderStatuses.NOT_PROCESSED),
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

    // TODO: 21.01.2020 CREATE IMPL
    @Override
    public Order read(long id) throws RepositoryException {
        throw new RepositoryException("Not supported yet.");
    }

    // TODO: 21.01.2020 CREATE IMPL
    @Override
    public List<Order> readAll() throws RepositoryException {
        throw new RepositoryException("Not supported yet.");
    }

    // TODO: 21.01.2020 CREATE IMPL
    @Override
    public List<Order> readFirst(int sliceSize) throws RepositoryException {
        throw new RepositoryException("Not supported yet.");
    }
}
