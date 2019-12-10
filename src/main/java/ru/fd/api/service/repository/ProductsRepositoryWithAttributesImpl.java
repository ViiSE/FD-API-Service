package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Attributes;
import ru.fd.api.service.entity.ProductAttributesDefaultImpl;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.mapper.ProductsWithAttributesRowMapper;

import java.util.ArrayList;
import java.util.Map;

@Repository("productsRepositoryWithAttributes")
@Scope("prototype")
public class ProductsRepositoryWithAttributesImpl implements ProductsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final ProductsRepository productsRepository;
    private final ProductProducer productProducer;
    private final SQLQueryCreator<String, String> sqlQueryCreator;

    public ProductsRepositoryWithAttributesImpl(
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
            Map<String, Attributes> attrForProducts = jdbcTemplate.queryForObject(
                    sqlQueryCreator.create("products_with_attr.sql").content(),
                    new ProductsWithAttributesRowMapper());

            if (attrForProducts != null) {
                products.forEach(product ->
                        products.decorateProduct(
                                product.id(),
                                productProducer.getProductWithAttributesInstance(
                                        product,
                                        attrForProducts.getOrDefault(
                                                product.id(),
                                                new ProductAttributesDefaultImpl(new ArrayList<>())))));
            }
            return products;
        } catch (CreatorException ex) {
            throw new RepositoryException(ex.getMessage(), ex.getCause());
        }
    }
}
