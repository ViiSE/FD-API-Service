/*
 *  Copyright 2019 ViiSE.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.constant.OrderResponses;
import ru.fd.api.service.data.OrderPojo;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.entity.OrderProducts;
import ru.fd.api.service.entity.OrderResponse;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.repository.mapper.OrderResponseDefaultRowMapper;

import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

@Repository("orderRepositoryDefault")
public class OrderRepositoryDefaultImpl implements OrderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final Order order;
    private final SQLQueryCreator<String, String> sqlQueryCreator;
    private final OrderResponseProducer orderResponseProducer;

    public OrderRepositoryDefaultImpl(
            Order order,
            SQLQueryCreator<String, String> sqlQueryCreator,
            OrderResponseProducer orderResponseProducer) {
        this.order = order;
        this.sqlQueryCreator = sqlQueryCreator;
        this.orderResponseProducer = orderResponseProducer;
    }

    @Override
    public long insert() throws RepositoryException {
        try {
            OrderPojo orderPojo = (OrderPojo) order.formForSend();
            jdbcTemplate.update(
                    sqlQueryCreator.create("order_create.sql").content(),
                    orderPojo.getId(),
                    orderPojo.getCityId(),
                    orderPojo.getCustomer().getEmail().getBytes("windows-1251"),
                    orderPojo.getCustomer().getInn().getBytes("windows-1251"),
                    orderPojo.getCustomer().getKpp().getBytes("windows-1251"),
                    orderPojo.getCustomer().getType(),
                    orderPojo.getDelivery().getCityId(),
                    orderPojo.getDelivery().getType(),
                    orderPojo.getDelivery().getAddress().getBytes("windows-1251"),
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
            return orderPK;
        } catch (CreatorException ex) {
            // TODO: 27.12.2019 FIX LATER A
        } catch (UnsupportedEncodingException ex) {
            // TODO: 27.12.2019 FIX LATER B
        } catch (DataAccessException ex) {
            // TODO: 27.12.2019 FIX LATER C
        } catch (NullPointerException ex) {
            // TODO: 27.12.2019 FIX ME LATER D
        }

    }

    @Override
    public OrderResponse read(long orderPK) throws RepositoryException {
        jdbcTemplate.setQueryTimeout(5);
        try {
            while (true) {
                OrderResponse response = jdbcTemplate.queryForObject(
                        sqlQueryCreator.create("order_status.sql"),
                        new Object[]{orderPK},
                        new OrderResponseDefaultRowMapper(orderResponseProducer));
                if (response.status() == OrderResponses.PROCESSED)
                    return orderResponseProducer.getOrderResponseWithMessageInstance(response, "Заказ в обработке");
                else if (response.status() == OrderResponses.LACK_OF_PRODUCTS_QUANTITY) {
                    OrderProducts orderProducts = jdbcTemplate.queryForObject(
                            sqlQueryCreator.create("order_products_lack.sql"),
                            new Object[]{orderPK},
                            new OrderProductsLackRowMapper(orderResponseProducer));
                    return orderResponseProducer.getOrderResponseWithProductsInstance(
                            response,
                            orderProducts);
                }
            }
        } catch(QueryTimeoutException ex) {
            // FIXME: 27.12.2019 FIX LATER A
        } catch(DataAccessException ex) {
            // FIXME: 27.12.2019 FIX LATER B
        }
    }
}
