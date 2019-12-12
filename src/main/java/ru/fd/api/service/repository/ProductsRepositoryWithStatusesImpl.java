package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Product;
import ru.fd.api.service.entity.ProductStatusesImpl;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.entity.Statuses;
import ru.fd.api.service.exception.CreatorException;
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
    private final SQLQueryCreator<String, String> sqlQueryCreator;

    public ProductsRepositoryWithStatusesImpl(
            ProductsRepository productsRepository,
            ProductProducer productProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        this.productsRepository = productsRepository;
        this.productProducer = productProducer;
        this.sqlQueryCreator = sqlQueryCreator;
    }

    @Override
    public Products readProducts() throws RepositoryException {
        try {
            Products products = productsRepository.readProducts();
            Map<String, Statuses> statusesForProducts = jdbcTemplate.queryForObject(
                    sqlQueryCreator.create("products_with_statuses.sql").content(),
                    new ProductsWithStatusesRowMapper());
            if(statusesForProducts != null)
                for(Product product: products) {
                    products.decorateProduct(
                            product.key(),
                            productProducer.getProductWithStatusesInstance(
                                    product,
                                    statusesForProducts.getOrDefault(
                                            product.id(),
                                            new ProductStatusesImpl(new ArrayList<>()))));
                }

            return products;
        } catch (CreatorException ex) {
            throw new RepositoryException(ex.getMessage(), ex.getCause());
        }
    }
}
