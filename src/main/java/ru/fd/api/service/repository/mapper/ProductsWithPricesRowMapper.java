package ru.fd.api.service.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.fd.api.service.entity.Attributes;
import ru.fd.api.service.entity.Price;
import ru.fd.api.service.entity.Prices;
import ru.fd.api.service.entity.Product;
import ru.fd.api.service.producer.entity.PriceProducer;
import ru.fd.api.service.producer.entity.PricesProducer;
import ru.fd.api.service.producer.entity.ProductProducer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductsWithPricesRowMapper implements RowMapper<Product> {

    private final Product product;
    private final PriceProducer priceProducer;
    private final PricesProducer pricesProducer;
    private final ProductProducer productProducer;

    public ProductsWithPricesRowMapper(
            Product product, PriceProducer priceProducer, PricesProducer pricesProducer, ProductProducer productProducer) {
        this.product = product;
        this.priceProducer = priceProducer;
        this.pricesProducer = pricesProducer;
        this.productProducer = productProducer;
    }

    @Override
    public Product mapRow(ResultSet rs, int i) throws SQLException {
        List<Price> prices = new ArrayList<>();
        do {
            String department_id = rs.getString("GID_DEP").trim();
            float value = rs.getFloat("PRICE");
            prices.add(priceProducer.getPriceDefaultInstance(department_id, value));
        } while(rs.next());

        return productProducer.getProductWithPricesInstance(product, pricesProducer.getPricesDefaultInstance(prices));
    }
}
