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
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.constant.Notifications;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Customer;
import ru.fd.api.service.entity.CustomerWithNotified;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.CustomerProducer;
import ru.fd.api.service.repository.mapper.CustomerWithNotifiedRowMapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Repository("customerRepositoryWithNotified")
public class CustomerRepositoryWithNotifiedImpl implements CustomerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final CustomerProducer customerProducer;
    private final SQLQueryCreator<String, String> sqlQueryCreator;

    public CustomerRepositoryWithNotifiedImpl(
            CustomerProducer customerProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        this.customerProducer = customerProducer;
        this.sqlQueryCreator = sqlQueryCreator;
    }

    @Override
    public List<Customer> readAll() throws RepositoryException {
        try {
            return jdbcTemplate.query(
                    sqlQueryCreator.create("customers.sql").content(),
                    new CustomerWithNotifiedRowMapper(customerProducer));
        } catch (DataAccessException | CreatorException ex) {
            throw new RepositoryException(ex.getMessage());
        }
    }

    @Override
    public void update(List<Customer> customers) throws RepositoryException {
        try {
            List<CustomerWithNotified> customersWN =
                    customers.stream().map(customer -> (CustomerWithNotified) customer).collect(Collectors.toList());
            jdbcTemplate.batchUpdate(
                    sqlQueryCreator.create("customers_notify.sql").content(),
                    new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                            ps.setShort(1, Notifications.IS_NOTIFIED);
                            ps.setLong(2, customersWN.get(i).orderId());
                        }

                        @Override
                        public int getBatchSize() {
                            return customersWN.size();
                        }
                    });
        } catch (DataAccessException | CreatorException ex) {
            throw new RepositoryException(ex.getMessage());
        }
    }
}
