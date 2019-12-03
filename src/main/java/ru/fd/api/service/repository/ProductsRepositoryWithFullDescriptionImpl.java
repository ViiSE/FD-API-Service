package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.entity.Product;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.mapper.ProductsWithFullDescriptionRowMapper;

import java.util.Map;

@Repository("productsRepositoryWithFullDescription")
public class ProductsRepositoryWithFullDescriptionImpl implements ProductsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final ProductsRepository productsRepository;
    private final ProductProducer productProducer;

    public ProductsRepositoryWithFullDescriptionImpl(ProductsRepository productsRepository, ProductProducer productProducer) {
        this.productsRepository = productsRepository;
        this.productProducer = productProducer;
    }

    @Override
    public Products readProducts() throws RepositoryException {
        Products products = productsRepository.readProducts();
        Map<String, String> fullDescForProducts = jdbcTemplate.queryForObject("SQL HERE", new ProductsWithFullDescriptionRowMapper());
        if (fullDescForProducts != null) {
            fullDescForProducts.forEach((id, fullDesc) -> {
                Product product = products.findProductById(id);
                if(product != null)
                    products.decorateProduct(id, productProducer.getProductWithFullDescription(product, fullDesc));
            });
        }
        return products;
    }
}
