package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Product;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.mapper.ProductsWithFullDescriptionRowMapper;

import java.util.Map;

@Repository("productsRepositoryWithFullDescription")
@Scope("prototype")
public class ProductsRepositoryWithFullDescriptionImpl implements ProductsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final ProductsRepository productsRepository;
    private final ProductProducer productProducer;
    private final SQLQueryCreator<String, String> sqlQueryCreator;

    public ProductsRepositoryWithFullDescriptionImpl(
            ProductsRepository productsRepository,
            ProductProducer productProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        this.productsRepository = productsRepository;
        this.productProducer = productProducer;
        this.sqlQueryCreator = sqlQueryCreator;
    }

    @Override
    public Products read() throws RepositoryException {
        try {
            Products products = productsRepository.read();
            Map<String, String> fullDescForProducts = jdbcTemplate.queryForObject(
                    sqlQueryCreator.create("products_with_full_description.sql").content(),
                    new ProductsWithFullDescriptionRowMapper());
            if (fullDescForProducts != null) {
                fullDescForProducts.forEach((id, fullDesc) -> {
                    Product product = products.findProductById(id);
                    if (product != null)
                        products.decorateProduct(id, productProducer.getProductWithFullDescriptionInstance(product, fullDesc));
                });
            }
            return products;
        } catch (CreatorException ex) {
            throw new RepositoryException(ex.getMessage(), ex.getCause());
        }
    }
}
