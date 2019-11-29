package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.entity.*;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.mapper.ProductsWithBalancesRowMapper;

import java.util.Map;

@Repository("productsRepositoryWithBalances")
public class ProductsRepositoryWithBalancesImpl implements ProductsRepository {

    @Autowired private JdbcTemplate jdbcTemplate;

    private final ProductsRepository productsRepository;
    private final ProductProducer productProducer;

    public ProductsRepositoryWithBalancesImpl(ProductsRepository productsRepository, ProductProducer productProducer) {
        this.productsRepository = productsRepository;
        this.productProducer = productProducer;
    }

    @Override
    public Products readProducts() throws RepositoryException {
        Products products = productsRepository.readProducts();
        Map<String, Balances> balanceForProducts = jdbcTemplate.queryForObject("SQL HERE", new ProductsWithBalancesRowMapper());
        if (balanceForProducts != null) {
            balanceForProducts.forEach((id, balances) -> {
                Product product = products.findProductById(id);
                if(product != null)
                    products.decorateProduct(id, productProducer.getProductWithBalancesInstance(product, balances));
            });
        }
        return products;
    }
}
