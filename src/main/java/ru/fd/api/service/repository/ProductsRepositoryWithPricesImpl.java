package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Prices;
import ru.fd.api.service.entity.Product;
import ru.fd.api.service.entity.ProductWithPricesImpl;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.PriceProducer;
import ru.fd.api.service.producer.entity.PricesProducer;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.mapper.ProductsWithPricesRowMapper;

import java.util.Map;

@Repository("productsRepositoryWithPrices")
@Scope("prototype")
public class ProductsRepositoryWithPricesImpl implements ProductsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final ProductsRepository productsRepository;
    private final ProductProducer productProducer;
    private final PriceProducer priceProducer;
    private final PricesProducer pricesProducer;
    private final SQLQueryCreator<String, String> sqlQueryCreator;

    public ProductsRepositoryWithPricesImpl(
            ProductsRepository productsRepository,
            ProductProducer productProducer,
            PriceProducer priceProducer,
            PricesProducer pricesProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        this.productsRepository = productsRepository;
        this.productProducer = productProducer;
        this.priceProducer = priceProducer;
        this.pricesProducer = pricesProducer;
        this.sqlQueryCreator = sqlQueryCreator;
    }

    @Override
    public Products read() throws RepositoryException {
          try {
              Products products = productsRepository.read();
              Map<String, Prices> pricesForProducts = jdbcTemplate.queryForObject(
                      sqlQueryCreator.create("products_with_prices.sql").content(),
                      new ProductsWithPricesRowMapper(priceProducer, pricesProducer));

              if(pricesForProducts != null)
                  for(Product product: products) {
                      if(pricesForProducts.containsKey(product.id())) {
                          products.decorateProduct(
                                  product.key(),
                                  productProducer.getProductWithPricesInstance(
                                          product,
                                          pricesForProducts.get(product.id())));
                      }
                  }

              products.removeProducts(ProductWithPricesImpl.class);

              return products;
          } catch (DataAccessException | CreatorException ex) {
              throw new RepositoryException(ex.getMessage(), ex.getCause());
          }
    }
}
