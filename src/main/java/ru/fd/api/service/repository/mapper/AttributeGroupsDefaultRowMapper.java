package ru.fd.api.service.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.fd.api.service.entity.AttributeGroups;
import ru.fd.api.service.producer.entity.AttributeGroupsProducer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AttributeGroupsDefaultRowMapper implements RowMapper<AttributeGroups> {

    private final AttributeGroupsProducer attributeGroupsProducer;

    public AttributeGroupsDefaultRowMapper(AttributeGroupsProducer attributeGroupsProducer) {
        this.attributeGroupsProducer = attributeGroupsProducer;
    }

    //    private final ProductProducer productProducer;

//    public ProductsSimpleRowMapper(ProductProducer productProducer) {
//        this.productProducer = productProducer;
//    }

    @Override
    public AttributeGroups mapRow(ResultSet rs, int i) throws SQLException {
//        List<Product> products = new ArrayList<>();
//        while(rs.next()) {
//            String id = rs.getString("TOVAR.GID");
//            String categoryId = rs.getString("DIRECTMAIN.IDENT");
//            String vendorId = rs.getString("MAKER.GID");
//            String unitId = rs.getString("OKEI");
//            String name = rs.getString("TOVAR.NAME");
//            short tax = rs.getShort("TAX");
//            String articul = "";
//            String code = rs.getString("TOVAR.GID");
//            boolean noReturn = rs.getBoolean("NO_RETURN");
//
//            products.add(productProducer.getProductProducerDefaultInstance(
//                    id,
//                    categoryId,
//                    vendorId,
//                    unitId,
//                    name,
//                    tax,
//                    articul,
//                    code,
//                    noReturn));
//        }
//        return new ProductsDefaultImpl(products);
        return null;
    }
}
