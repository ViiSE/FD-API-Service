package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.mapper.ProductsSimpleRowMapper;

@Repository("productsRepositorySimple")
public class ProductsRepositorySimpleImpl implements ProductsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProductProducer productProducer;

    @Override
    public Products readProducts() throws RepositoryException {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT b.Name as tName, UUID_TO_CHAR(t.GID) as GIDTovar, coalesce(Left(dm.Ident, 11), '') as idCategory, coalesce(UUID_TO_CHAR(m.GID), '') as GIDMaker, coalesce(e.OKEI, 796) as OKEI,\n" +
                    "       case when coalesce(t.ProcNDS, 20) = 0 or coalesce(t.ProcNDS, 20) = 18 then 20 else coalesce(t.ProcNDS, 20) end as TAX,\n" +
                    "     t.Ident as tIdent\n" +
                    "FROM Branch b\n" +
                    "LEFT JOIN Tovar t on t.Kod = b.KodLeaf\n" +
                    "LEFT JOIN DirectMainTovar dmt on dmt.Tovar = b.KodLeaf\n" +
                    "LEFT JOIN DirectMain dm on dm.Kod = dmt.DirectMain\n" +
                    "LEFT JOIN Maker m on m.Kod = t.Maker\n" +
                    "LEFT JOIN Edism e on e.Name = t.Edism\n" +
                    "WHERE b.KodTree = 1\n" +
                    "AND b.Owner = (SELECT bb.Kod FROM Branch bb WHERE bb.KodTree = 1 AND bb.Owner = 0 AND bb.KodLeaf = -1 AND bb.Name = 'Товары')\n" +
                    "AND exists (SELECT 1 FROM PriceTovarHI ph LEFT JOIN GetPrice(ph.price, ph.Tovar, cast('NOW' as date)) gp on 1=1 WHERE ph.Tovar = t.Kod AND coalesce(gp.Price, 0) <> 0)" +
                    "ORDER BY 2", new ProductsSimpleRowMapper(productProducer));
        } catch (DataAccessException ex) {
            throw new RepositoryException(ex.getMessage(), ex.getCause());
        }
    }
}
