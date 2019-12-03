package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.entity.Prices;
import ru.fd.api.service.entity.Product;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.mapper.ProductsWithPricesRowMapper;

import java.util.Map;

@Repository("productsRepositoryWithPrices")
public class ProductsRepositoryWithPricesImpl implements ProductsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final ProductsRepository productsRepository;
    private final ProductProducer productProducer;

    public ProductsRepositoryWithPricesImpl(ProductsRepository productsRepository, ProductProducer productProducer) {
        this.productsRepository = productsRepository;
        this.productProducer = productProducer;
    }

    @Override
    public Products readProducts() throws RepositoryException {
        Products products = productsRepository.readProducts();
        Map<String, Prices> pricesForProducts = jdbcTemplate.queryForObject("SQL HERE", new ProductsWithPricesRowMapper());
        if (pricesForProducts != null) {
            pricesForProducts.forEach((id, prices) -> {
                Product product = products.findProductById(id);
                if(product != null)
                    products.decorateProduct(id, productProducer.getProductWithPricesInstance(product, prices));
            });
        }
        return products;
    }
}
