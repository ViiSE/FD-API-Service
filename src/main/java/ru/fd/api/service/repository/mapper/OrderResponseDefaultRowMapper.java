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

package ru.fd.api.service.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.fd.api.service.entity.OrderResponse;
import ru.fd.api.service.producer.entity.OrderResponseProducer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderResponseDefaultRowMapper implements RowMapper<OrderResponse> {

    private final OrderResponseProducer orderResponseProducer;

    public OrderResponseDefaultRowMapper(OrderResponseProducer orderResponseProducer) {
        this.orderResponseProducer = orderResponseProducer;
    }

    @Override
    public OrderResponse mapRow(ResultSet rs, int i) throws SQLException {
        short status = rs.getShort("STATUS");
        return orderResponseProducer.getOrderResponseProducerDefaultInstance(status);
    }
}
