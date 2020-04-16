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
import ru.fd.api.service.entity.Balance;
import ru.fd.api.service.entity.Balances;
import ru.fd.api.service.producer.entity.BalanceProducer;
import ru.fd.api.service.producer.entity.BalancesProducer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Component("rmProductsWithBalances")
public class RmProductsWithBalancesImpl implements RowMapper<Map<String, Balances>> {

    private final BalanceProducer balanceProducer;
    private final BalancesProducer balancesProducer;

    public RmProductsWithBalancesImpl(BalanceProducer balanceProducer, BalancesProducer balancesProducer) {
        this.balanceProducer = balanceProducer;
        this.balancesProducer = balancesProducer;
    }

    @Override
    public Map<String, Balances> mapRow(ResultSet rs, int i) throws SQLException {
        Map<String, Balances> balancesMap = new HashMap<>();
        String id = "";
        List<Balance> balances = new ArrayList<>();
        do {
            if(id.isEmpty())
                id = rs.getString("GID_TOVAR").trim();
            if(!id.equals(rs.getString("GID_TOVAR"))) {
                balancesMap.put(id, balancesProducer.getBalancesInstance(new ArrayList<>(balances)));
                id = rs.getString("GID_TOVAR").trim();
                balances.clear();
            }

            String department_id = rs.getString("GID_DEP").trim();
            int quantity = rs.getInt("QUANTITY");
            balances.add(balanceProducer.getBalanceInstance(department_id, quantity));
        } while(rs.next());

        balancesMap.put(id, balancesProducer.getBalancesInstance(new ArrayList<>(balances)));

        return balancesMap;
    }
}
