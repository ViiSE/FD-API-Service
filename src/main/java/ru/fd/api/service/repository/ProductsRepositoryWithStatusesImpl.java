package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.entity.ProductStatusesImpl;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.entity.Statuses;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.mapper.ProductsWithStatusesRowMapper;

import java.util.ArrayList;
import java.util.Map;

@Repository("productsRepositoryWithStatuses")
@Scope("prototype")
public class ProductsRepositoryWithStatusesImpl implements ProductsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final ProductsRepository productsRepository;
    private final ProductProducer productProducer;

    public ProductsRepositoryWithStatusesImpl(ProductsRepository productsRepository, ProductProducer productProducer) {
        this.productsRepository = productsRepository;
        this.productProducer = productProducer;
    }

    @Override
    public Products readProducts() throws RepositoryException {
        Products products = productsRepository.readProducts();
        Map<String, Statuses> statusesForProducts = jdbcTemplate.queryForObject("SQL HERE", new ProductsWithStatusesRowMapper());
        if (statusesForProducts != null) {
            products.forEach(product ->
                    products.decorateProduct(
                            product.id(),
                            productProducer.getProductWithStatusesInstance(
                                    product,
                                    statusesForProducts.getOrDefault(
                                            product.id(),
                                            new ProductStatusesImpl(new ArrayList<>())))));
        }
        return products;
    }
}
