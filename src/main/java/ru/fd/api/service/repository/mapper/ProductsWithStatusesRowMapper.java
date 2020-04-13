package ru.fd.api.service.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.fd.api.service.entity.Statuses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Component("productsWithStatusesRowMapper")
public class ProductsWithStatusesRowMapper implements RowMapper<Map<String, Statuses>> {

    @Override
    public Map<String, Statuses> mapRow(ResultSet rs, int i) throws SQLException {
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
