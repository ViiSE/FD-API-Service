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
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.producer.entity.OrderProducer;

import java.sql.ResultSet;
import java.sql.SQLException;

//@Component("rmOrderChangedBalances")
public class RmOrderChangedBalancesImpl implements RowMapper<Order> {

    private final OrderProducer orderProducer;

    public RmOrderChangedBalancesImpl(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @Override
    public Order mapRow(ResultSet rs, int i) throws SQLException {
        long id = rs.getLong("KOD");
        short status = rs.getShort("STATUS");
        return orderProducer.getOrderSimpleInstance(id, status);
    }
}
