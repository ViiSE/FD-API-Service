package test.creator;

import ru.fd.api.service.entity.Product;
import ru.fd.api.service.entity.ProductSimpleImpl;

public class ProductCreatorTestImpl implements ProductCreator {

    @Override
    public Product createProduct() {
        String id = "id_1";
        String categoryId = "category_1";
        String vendorId = "vendor_1";
        String unitId = "unit_1";
        String name = "Item_1";
        short tax = 20;
        String articul = "art_1";
        String code = "code_1";

        return new ProductSimpleImpl(id, categoryId, vendorId, unitId, name, tax, articul, code);
    }
}
