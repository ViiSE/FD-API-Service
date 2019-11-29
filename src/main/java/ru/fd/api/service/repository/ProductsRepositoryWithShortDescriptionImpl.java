package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.entity.Product;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.mapper.ProductsWithShortDescriptionRowMapper;

import java.util.Map;

@Repository("productsRepositoryWithShortDescription")
public class ProductsRepositoryWithShortDescriptionImpl implements ProductsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final ProductsRepository productsRepository;
    private final ProductProducer productProducer;

    public ProductsRepositoryWithShortDescriptionImpl(ProductsRepository productsRepository, ProductProducer productProducer) {
        this.productsRepository = productsRepository;
        this.productProducer = productProducer;
    }

    @Override
    public Products readProducts() throws RepositoryException {
        Products products = productsRepository.readProducts();
        Map<String, String> shortDescForProducts = jdbcTemplate.queryForObject("SQL HERE", new ProductsWithShortDescriptionRowMapper());
        if (shortDescForProducts != null) {
            shortDescForProducts.forEach((id, shortDesc) -> {
                Product product = products.findProductById(id);
                if(product != null)
                    products.decorateProduct(id, productProducer.getProductWithShortDescription(product, shortDesc));
            });
        }
        return products;
    }
}
