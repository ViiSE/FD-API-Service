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

package ru.fd.api.service.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.fd.api.service.entity.Customer;
import ru.fd.api.service.producer.entity.CustomerProducer;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component("customerWithNotifiedRowMapper")
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
