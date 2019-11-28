package test.producer;

import ru.fd.api.service.entity.Product;
import ru.fd.api.service.entity.ProductDefaultImpl;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

public class ProductCreatorTestImpl implements ProductCreator {

    @Override
    public Product createProduct() {
        String id = "id_111222333444";
        String categoryId = "category_345213";
        String vendorId = "vendor_225674";
        String unitId = "unit_32";
        String name = "Item 1";
        int tax = 20;
        String articul = "art_3305";
        String code = "code_22505";

        return new ProductDefaultImpl(id, categoryId, vendorId, unitId, name, tax, articul, code, false);
    }
}
