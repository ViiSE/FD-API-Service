package ru.fd.api.service.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.fd.api.service.entity.Attributes;
import ru.fd.api.service.entity.Balance;
import ru.fd.api.service.entity.Balances;
import ru.fd.api.service.entity.Product;
import ru.fd.api.service.producer.entity.BalanceProducer;
import ru.fd.api.service.producer.entity.BalancesProducer;
import ru.fd.api.service.producer.entity.ProductProducer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsWithBalancesRowMapper implements RowMapper<Map<String, Balances>> {

    private final BalanceProducer balanceProducer;
    private final BalancesProducer balancesProducer;

    public ProductsWithBalancesRowMapper(BalanceProducer balanceProducer, BalancesProducer balancesProducer) {
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
                id = rs.getString("GID_TOVAR");
            if(!id.equals(rs.getString("GID_TOVAR"))) {
                balancesMap.put(id, balancesProducer.getBalancesDefaultInstance(new ArrayList<>(balances)));
                id = rs.getString("GID_TOVAR");
                balances.clear();
            }

            String department_id = rs.getString("GID_DEP");
            int quantity = rs.getInt("QUANTITY");
            balances.add(balanceProducer.getBalanceDefaultInstance(department_id, quantity));
        } while(rs.next());

        return balancesMap;
    }
}
