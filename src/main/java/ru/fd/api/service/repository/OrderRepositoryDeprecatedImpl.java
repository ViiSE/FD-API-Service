/*
 *  Copyright 2019 FD Company. All rights reserved.
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
import ru.fd.api.service.constant.OrderStatuses;
import ru.fd.api.service.data.OrderPojo;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.entity.OrderResponse;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.OrderResponseProducer;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.entity.ProductsProducer;
import ru.fd.api.service.repository.mapper.OrderProductsLackRowMapper;
import ru.fd.api.service.repository.mapper.OrderResponseSimpleRowMapper;

import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository("orderRepositoryDeprecated")
@Scope("prototype")
@Deprecated
public class OrderRepositoryDeprecatedImpl implements OrderRepository<Long, OrderResponse> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final Order order;
    private final SQLQueryCreator<String, String> sqlQueryCreator;
    private final ProductProducer productProducer;
    private final ProductsProducer orderProductsProducer;
    private final OrderResponseProducer orderResponseProducer;

    public OrderRepositoryDeprecatedImpl(
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
                    orderPojo.getCityId(),
                    orderPojo.getCustomer().getPhoneNumber().getBytes("windows-1251"),
                    orderPojo.getCustomer().getEmail().getBytes("windows-1251"),
                    orderPojo.getCustomer().getInn().getBytes("windows-1251"),
                    orderPojo.getCustomer().getKpp().getBytes("windows-1251"),
                    orderPojo.getCustomer().getType(),
                    orderPojo.getDelivery().getCityId(),
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
    public OrderResponse read(long id) throws RepositoryException {
        jdbcTemplate.setQueryTimeout(1);
        try {
            while (true) {
                OrderResponse response = jdbcTemplate.queryForObject(
                        sqlQueryCreator.create("order_status.sql").content(),
                        new Object[]{id},
                        new OrderResponseSimpleRowMapper(id, orderResponseProducer));
                if(response != null) {
                    if (response.status() == OrderStatuses.READY_TO_ASSEMBLY)
                        return orderResponseProducer.getOrderResponseWithMessageInstance(response, "Заказ в обработке");
                    else if (response.status() == OrderStatuses.LACK_OF_PRODUCTS_QUANTITY) {
                        Products orderProducts = jdbcTemplate.queryForObject(
                                sqlQueryCreator.create("order_products_lack.sql").content(),
                                new Object[]{id},
                                new OrderProductsLackRowMapper(productProducer, orderProductsProducer));
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

    @Override
    public List<OrderResponse> readAll() throws RepositoryException {
        throw new RepositoryException("Not supported yet.");
    }

    @Override
    public List<OrderResponse> readFirst(int sliceSize) throws RepositoryException {
        throw new RepositoryException("Not supported yet.");
    }
}
