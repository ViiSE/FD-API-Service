package test.producer;

import ru.fd.api.service.creator.ProductsCreator;
import ru.fd.api.service.entity.Product;
import ru.fd.api.service.entity.ProductDefaultImpl;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.entity.ProductsDefaultImpl;

import java.util.ArrayList;
import java.util.List;

public class ProductsCreatorTestImpl implements ProductsCreator {

    @Override
    public Products create() {
        List<Product> products = new ArrayList<>();

        for(int i = 1; i <= 10; i++)
            products.add(createProduct(i));

        return new ProductsDefaultImpl(products);
    }

    private Product createProduct(int id) {
        return new ProductDefaultImpl(
                "id_" + id,
                "category_" + id,
                "vendor_" + id,
                "unit_" + id,
                "Item_" + id,
                (short) 20,
                "art_" + id,
                "code_" + id,
                false);
    }
}
