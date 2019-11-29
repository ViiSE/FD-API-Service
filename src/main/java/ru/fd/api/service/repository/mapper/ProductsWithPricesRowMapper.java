package ru.fd.api.service.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.fd.api.service.entity.Attributes;
import ru.fd.api.service.entity.Prices;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ProductsWithPricesRowMapper implements RowMapper<Map<String, Prices>> {

    @Override
    public Map<String, Prices> mapRow(ResultSet rs, int i) throws SQLException {
//        List<Product> products = new ArrayList<>();
//        while(rs.next()) {
//            String id = rs.getString("GID");
//            String ...
//            products.add(...);//new ProductDefaultImpl());
//        }
        //return new ProductsDefaultImpl(products);
        return null;
    }
}
