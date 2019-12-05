package ru.fd.api.service.repository.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import ru.fd.api.service.entity.Product;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.entity.ProductsDefaultImpl;
import ru.fd.api.service.producer.entity.ProductProducer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsSimpleRowMapper implements RowMapper<Products> {

    private final ProductProducer productProducer;

    public ProductsSimpleRowMapper(ProductProducer productProducer) {
        this.productProducer = productProducer;
    }

    @Override
    public Products mapRow(ResultSet rs, int i) throws SQLException {
        List<Product> products = new ArrayList<>();
        while(rs.next()) {
            String id = rs.getString("GIDTovar").trim();
            String categoryId = rs.getString("idCategory").trim();
            String vendorId = rs.getString("GIDMaker").trim();
            String unitId = rs.getString("OKEI").trim();
            String name = rs.getString("tName").trim().replaceFirst("\\. ", "");
            short tax = rs.getShort("TAX");
            String articul = "";
            String code = rs.getString("tIdent").trim();

            products.add(productProducer.getProductSimpleInstance(
                    id,
                    categoryId,
                    vendorId,
                    unitId,
                    name,
                    tax,
                    articul,
                    code));
        }
        return new ProductsDefaultImpl(products);
    }
}
