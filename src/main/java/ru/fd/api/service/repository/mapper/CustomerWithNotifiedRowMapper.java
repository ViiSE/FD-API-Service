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
import ru.fd.api.service.entity.Customer;
import ru.fd.api.service.producer.entity.CustomerProducer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerWithNotifiedRowMapper implements RowMapper<Customer> {

    private final CustomerProducer customerProducer;

    public CustomerWithNotifiedRowMapper(CustomerProducer customerProducer) {
        this.customerProducer = customerProducer;
    }

    @Override
    public Customer mapRow(ResultSet rs, int i) throws SQLException {
        long orderId = rs.getLong("KOD");
        short notified = rs.getShort("NOTIFIED");
        String phoneNumber = rs.getString("CUST_PN");
        String email = rs.getString("CUST_EMAIL");
        short type = rs.getShort("CUST_TYPE");

        Customer customer = customerProducer.getCustomerSimpleInstance(type);
        if(!phoneNumber.isEmpty())
            customer = customerProducer.getCustomerWithPhoneNumberInstance(customer, phoneNumber);
        if(!email.isEmpty())
            customer = customerProducer.getCustomerWithEmailInstance(customer, email);

        return customerProducer.getCustomerWithNotifiedInstance(customer, notified, orderId);
    }
}
