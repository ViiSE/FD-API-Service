package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.entity.Product;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.PriceProducer;
import ru.fd.api.service.producer.entity.PricesProducer;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.mapper.ProductsWithPricesRowMapper;

@Repository("productsRepositoryWithPrices")
@Scope("prototype")
public class ProductsRepositoryWithPricesImpl implements ProductsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired private PriceProducer priceProducer;
    @Autowired private PricesProducer pricesProducer;

    private final ProductsRepository productsRepository;
    private final ProductProducer productProducer;

    public ProductsRepositoryWithPricesImpl(ProductsRepository productsRepository, ProductProducer productProducer) {
        this.productsRepository = productsRepository;
        this.productProducer = productProducer;
    }

    @Override
    public Products readProducts() throws RepositoryException {
        // FIXME: 06.12.2019 CHANGE SQL
        Products products = productsRepository.readProducts();
        for (Product product : products) {
            try {
                Product productWithPrices = jdbcTemplate.queryForObject(
                        "SELECT distinct UUID_TO_CHAR(e.gid) as GID_DEP, gp.Price as PRICE\n" +
                                "FROM Tovar t\n" +
                                " LEFT JOIN PriceTovarHI ph on ph.Tovar = t.Kod\n" +
                                " LEFT JOIN DI_Values dv on dv.Field = (SELECT df.KOD FROM DI_FIELD df WHERE df.IDENT = 'Shop_PriceList')\n" +
                                "   AND dv.TypeRes = (SELECT tr.KOD FROM TYPERES tr WHERE tr.IDENT = 'Elu')\n" +
                                "   AND dv.FI = ph.price\n" +
                                " LEFT JOIN Elu e on e.Kod = dv.Resource\n" +
                                " LEFT JOIN SpDI_Values((SELECT df.KOD FROM DI_FIELD df WHERE df.IDENT = 'Elu_CloseTK'),\n" +
                                "                       (SELECT tr.KOD FROM TYPERES tr WHERE tr.IDENT = 'Elu'),\n" +
                                "                       e.KOD,\n" +
                                "                       cast('NOW' as date)) cl on 1=1\n" +
                                " LEFT JOIN GetPrice(ph.price, t.Kod, cast('NOW' as date)) gp on 1=1\n" +
                                "WHERE t.GID = CHAR_TO_UUID(?)\n" +
                                " AND (e.Ident starting 'ShopIndex_' or e.Ident starting 'Skl_')\n" +
                                " AND coalesce(cl.FI, 0) = 0\n" +
                                " AND e.Ident <> 'Skl_9'",
                        new Object[]{product.id()},
                        new ProductsWithPricesRowMapper(
                                product,
                                priceProducer,
                                pricesProducer,
                                productProducer));
                products.decorateProduct(product.id(), productWithPrices);
            } catch (DataAccessException ex) {
                throw new RepositoryException(ex.getMessage(), ex.getCause());
            }
        }

        return products;
    }
}
