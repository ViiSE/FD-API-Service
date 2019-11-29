package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.ProductPojo;

@Component("productDefault")
@Scope("prototype")
public class ProductDefaultImpl implements Product {

    private final String id;
    private final String categoryId;
    private final String vendorId;
    private final String unitId;
    private final String name;
    private final short tax;
    private final String articul;
    private final String code;
    private final boolean noReturn;

    public ProductDefaultImpl(
            String id, String categoryId, String vendorId, String unitId, String name,
            short tax, String articul, String code, boolean noReturn) {
        this.id = id;
        this.categoryId = categoryId;
        this.vendorId = vendorId;
        this.unitId = unitId;
        this.name = name;
        this.tax = tax;
        this.articul = articul;
        this.code = code;
        this.noReturn = noReturn;
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public Object formForSend() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setId(id);
        productPojo.setCategoryId(categoryId);
        productPojo.setVendorId(vendorId);
        productPojo.setUnitId(unitId);
        productPojo.setName(name);
        productPojo.setTax(tax);
        productPojo.setArticul(articul);
        productPojo.setCode(code);
        productPojo.setNoReturn(noReturn);
        return productPojo;
    }
}
