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
    public Products readProducts() throws RepositoryException {
          try {
              Products products = productsRepository.readProducts();
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

//     FIXME: 06.12.2019 CHANGE SQL
//    Products products = productsRepository.readProducts();
//        for (Product product : products) {
//                try {
//                Product productWithPrices = jdbcTemplate.queryForObject(
//                "SELECT distinct UUID_TO_CHAR(e.gid) as GID_DEP, gp.Price as PRICE\n" +
//                "FROM Tovar t\n" +
//                " LEFT JOIN PriceTovarHI ph on ph.Tovar = t.Kod\n" +
//                " LEFT JOIN DI_Values dv on dv.Field = (SELECT df.KOD FROM DI_FIELD df WHERE df.IDENT = 'Shop_PriceList')\n" +
//                "   AND dv.TypeRes = (SELECT tr.KOD FROM TYPERES tr WHERE tr.IDENT = 'Elu')\n" +
//                "   AND dv.FI = ph.price\n" +
//                " LEFT JOIN Elu e on e.Kod = dv.Resource\n" +
//                " LEFT JOIN SpDI_Values((SELECT df.KOD FROM DI_FIELD df WHERE df.IDENT = 'Elu_CloseTK'),\n" +
//                "                       (SELECT tr.KOD FROM TYPERES tr WHERE tr.IDENT = 'Elu'),\n" +
//                "                       e.KOD,\n" +
//                "                       cast('NOW' as date)) cl on 1=1\n" +
//                " LEFT JOIN GetPrice(ph.price, t.Kod, cast('NOW' as date)) gp on 1=1\n" +
//                "WHERE t.GID = CHAR_TO_UUID(?)\n" +
//                " AND (e.Ident starting 'ShopIndex_' or e.Ident starting 'Skl_')\n" +
//                " AND coalesce(cl.FI, 0) = 0\n" +
//                " AND e.Ident <> 'Skl_9'",
//                new Object[]{product.id()},
//                new ProductsWithPricesRowMapper(
//                product,
//                priceProducer,
//                pricesProducer,
//                productProducer));
//                products.decorateProduct(product.id(), productWithPrices);
//                } catch (DataAccessException ex) {
//                throw new RepositoryException(ex.getMessage(), ex.getCause());
//                }
//                }
//
//                return products;
