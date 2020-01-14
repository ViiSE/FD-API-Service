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
import org.springframework.dao.QueryTimeoutException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.constant.OrderResponses;
import ru.fd.api.service.data.OrderPojo;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.entity.OrderResponse;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.ProductsProducer;
import ru.fd.api.service.producer.entity.OrderResponseProducer;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.mapper.OrderProductsDefaultRowMapper;
import ru.fd.api.service.repository.mapper.OrderResponseSimpleRowMapper;

import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

@Repository("orderRepositoryDefault")
@Scope("prototype")
public class OrderRepositoryDefaultImpl implements OrderRepository<Long, OrderResponse> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final Order order;
    private final SQLQueryCreator<String, String> sqlQueryCreator;
    private final ProductProducer productProducer;
    private final ProductsProducer orderProductsProducer;
    private final OrderResponseProducer orderResponseProducer;

    public OrderRepositoryDefaultImpl(
            Order order,
            SQLQueryCreator<String, String> sqlQueryCreator,
            ProductProducer productProducer,
            ProductsProducer orderProductsProducer,
            OrderResponseProducer orderResponseProducer) {
        this.order = order;
        this.sqlQueryCreator = sqlQueryCreator;
        this.productProducer = productProducer;
        this.orderProductsProducer = orderProductsProducer;
        this.orderResponseProducer = orderResponseProducer;
    }

    @Override
    public Long insert() throws RepositoryException {
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
            return orderPK;
        } catch (CreatorException | DataAccessException ex) {
            throw new RepositoryException(ex.getMessage());
        } catch (UnsupportedEncodingException ex) {
            throw new RepositoryException("Кодировка не поддерживается");
        }
    }

    @Override
    public OrderResponse read(long orderPK) throws RepositoryException {
        jdbcTemplate.setQueryTimeout(1);
        try {
            while (true) {
                OrderResponse response = jdbcTemplate.queryForObject(
                        sqlQueryCreator.create("order_status.sql").content(),
                        new Object[]{orderPK},
                        new OrderResponseSimpleRowMapper(orderPK, orderResponseProducer));
                if(response != null) {
                    if (response.status() == OrderResponses.READY_TO_ASSEMBLY)
                        return orderResponseProducer.getOrderResponseWithMessageInstance(response, "Заказ в обработке");
                    else if (response.status() == OrderResponses.LACK_OF_PRODUCTS_QUANTITY) {
                        Products orderProducts = jdbcTemplate.queryForObject(
                                sqlQueryCreator.create("order_products_lack.sql").content(),
                                new Object[]{orderPK},
                                new OrderProductsDefaultRowMapper(productProducer, orderProductsProducer));
                        return orderResponseProducer.getOrderResponseWithProductsInstance(
                                response,
                                orderProducts);
                    }
                }
            }
        } catch(QueryTimeoutException ex) {
            throw new RepositoryException("Время обработки заказа истекло");
        } catch(DataAccessException | CreatorException ex) {
            throw new RepositoryException(ex.getMessage());
        }
    }
}
