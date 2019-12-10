package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.database.SQLQuery;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Balances;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.ReaderException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.database.SQLQueryProducer;
import ru.fd.api.service.producer.database.SQLReaderProducer;
import ru.fd.api.service.producer.entity.BalanceProducer;
import ru.fd.api.service.producer.entity.BalancesProducer;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.mapper.ProductsWithBalancesRowMapper;

import java.util.ArrayList;
import java.util.Map;

@Repository("productsRepositoryWithBalances")
@Scope("prototype")
public class ProductsRepositoryWithBalancesImpl implements ProductsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final ProductsRepository productsRepository;
    private final ProductProducer productProducer;
    private final BalanceProducer balanceProducer;
    private final BalancesProducer balancesProducer;
    private final SQLQueryCreator<String, String> sqlQueryCreator;

    public ProductsRepositoryWithBalancesImpl(
            ProductsRepository productsRepository,
            ProductProducer productProducer,
            BalanceProducer balanceProducer,
            BalancesProducer balancesProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        this.productsRepository = productsRepository;
        this.productProducer = productProducer;
        this.balanceProducer = balanceProducer;
        this.balancesProducer = balancesProducer;
        this.sqlQueryCreator = sqlQueryCreator;
    }

    @Override
    public Products readProducts() throws RepositoryException {
        try {
            Products products = productsRepository.readProducts();
            Map<String, Balances> balanceForProducts = jdbcTemplate.queryForObject(
                    sqlQueryCreator.create("products_with_balances.sql").content(),
                    new ProductsWithBalancesRowMapper(balanceProducer, balancesProducer));
//        Map<String, Balances> balanceForProducts = jdbcTemplate.queryForObject(
//                "SELECT UUID_TO_CHAR(t.GID) as GID_TOVAR, coalesce(UUID_TO_CHAR(e.GID), '') as GID_DEP, sum(coalesce(cr.Rest, 0)) as QUANTITY\n" +
//                "FROM Branch b\n" +
//                " LEFT JOIN Tovar t on t.Kod = b.KodLeaf\n" +
//                " LEFT JOIN CardRest cr on cr.Tovar = t.Kod\n" +
//                " LEFT JOIN Account a on a.Kod = cr.AccSkl\n" +
//                " LEFT JOIN Elu e on e.Kod = A.KodElu\n" +
//                " LEFT JOIN SpDI_Values((SELECT df.KOD FROM DI_FIELD df WHERE df.IDENT = 'Elu_CloseTK'),\n" +
//                "                        (SELECT tr.KOD FROM TYPERES tr WHERE tr.IDENT = 'Elu'),\n" +
//                "                       e.KOD,\n" +
//                "                       cast('NOW' as date)) cl on 1=1\n" +
//                "WHERE b.KodTree = 1\n" +
//                " AND b.Owner = (Select bb.Kod From Branch bb Where bb.KodTree = 1 and bb.Owner = 0 and bb.KodLeaf = -1 and bb.Name = 'Товары')\n" +
//                " AND cr.rest > 0\n" +
//                " AND (e.Ident starting 'ShopIndex_' or e.Ident starting 'Skl_')\n" +
//                " AND coalesce(cl.FI, 0) = 0\n" +
//                " AND e.Ident <> 'Skl_9'\n" +
//                "GROUP BY UUID_TO_CHAR(t.GID), UUID_TO_CHAR(e.GID)\n" +
//                "ORDER BY 1", new ProductsWithBalancesRowMapper(balanceProducer, balancesProducer));
            if (balanceForProducts != null)
                products.forEach(product ->
                        products.decorateProduct(
                                product.id(),
                                productProducer.getProductWithBalancesInstance(
                                        product,
                                        balanceForProducts.getOrDefault(
                                                product.id(),
                                                balancesProducer.getBalancesDefaultInstance(new ArrayList<>())))));

            return products;
        } catch (CreatorException ex) {
            throw new RepositoryException(ex.getMessage(), ex.getCause());
        }
    }
}
