package ru.fd.api.service.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.fd.api.service.entity.Attributes;
import ru.fd.api.service.entity.Product;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.entity.ProductsDefaultImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductsWithAttributesRowMapper implements RowMapper<Map<String, Attributes>> {

    @Override
    public Map<String, Attributes> mapRow(ResultSet rs, int i) throws SQLException {
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
