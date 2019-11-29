package test.repository;

import ru.fd.api.service.entity.*;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.ProductsRepository;
import test.creator.AttributesCreatorTestImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductsRepositoryWithAttributesTestImpl implements ProductsRepository {

    private final ProductsRepository productsRepository;
    private final ProductProducer productProducer;

    public ProductsRepositoryWithAttributesTestImpl(ProductsRepository productsRepository, ProductProducer productProducer) {
        this.productsRepository = productsRepository;
        this.productProducer = productProducer;
    }

    @Override
    public Products readProducts() throws RepositoryException {
        Products products = productsRepository.readProducts();

        Attributes attributes1 = new AttributesCreatorTestImpl().createAttributes();
        Attributes attributes2 = new AttributesDefaultImpl(new ArrayList<>() {{
            add(new AttributeDefaultImpl("attr_1", "value attr 1"));
            add(new AttributeDefaultImpl("attr_2", "value attr 2"));
            add(new AttributeDefaultImpl("attr_3", "value attr 3"));
        }});


        Map<String, Attributes> attrForProducts = new HashMap<>() {{
            put("id_1", attributes1);
            put("id_2", attributes2); }};

        attrForProducts.forEach((id, attr) -> {
            Product product = products.findProductById(id);
            if(product != null)
                products.decorateProduct(id, productProducer.getProductWithAttributesInstance(product, attr));
        });
        return products;
    }
}
