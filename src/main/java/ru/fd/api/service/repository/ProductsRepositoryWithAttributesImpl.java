package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.entity.*;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.mapper.ProductsWithAttributesRowMapper;

import java.util.Map;

@Repository("productsRepositoryWithAttributes")
public class ProductsRepositoryWithAttributesImpl implements ProductsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final ProductsRepository productsRepository;
    private final ProductProducer productProducer;

    public ProductsRepositoryWithAttributesImpl(ProductsRepository productsRepository, ProductProducer productProducer) {
        this.productsRepository = productsRepository;
        this.productProducer = productProducer;
    }

    @Override
    public Products readProducts() throws RepositoryException {
        Products products = productsRepository.readProducts();
        Map<String, Attributes> attrForProducts = jdbcTemplate.queryForObject("SQL HERE", new ProductsWithAttributesRowMapper());
        if (attrForProducts != null) {
            attrForProducts.forEach((id, attr) -> {
                Product product = products.findProductById(id);
                if(product != null)
                    products.decorateProduct(id, productProducer.getProductWithAttributesInstance(product, attr));
            });
        }
        return products;
    }
}
