package test.repository;

import ru.fd.api.service.entity.Attributes;
import ru.fd.api.service.entity.ProductAttributeDefaultImpl;
import ru.fd.api.service.entity.ProductAttributesDefaultImpl;
import ru.fd.api.service.entity.Products;
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
    public Products read() throws RepositoryException {
        Products products = productsRepository.read();

        Attributes attributes1 = new AttributesCreatorTestImpl().create();
        Attributes attributes2 = new ProductAttributesDefaultImpl(new ArrayList<>() {{
            add(new ProductAttributeDefaultImpl("attr_1", "value attr 1"));
            add(new ProductAttributeDefaultImpl("attr_2", "value attr 2"));
            add(new ProductAttributeDefaultImpl("attr_3", "value attr 3"));
        }});


        Map<String, Attributes> attrForProducts = new HashMap<>() {{
            put("id_1", attributes1);
            put("id_2", attributes2); }};

        products.forEach(product ->
            products.decorateProduct(
                    product.id(),
                    productProducer.getProductWithAttributesInstance(
                            product,
                            attrForProducts.getOrDefault(
                                    product.id(),
                                    new ProductAttributesDefaultImpl(new ArrayList<>())))));

        return products;
    }
}
