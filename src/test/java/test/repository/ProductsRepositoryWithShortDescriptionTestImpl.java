package test.repository;

import ru.fd.api.service.entity.*;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.ProductsRepository;

import java.util.HashMap;
import java.util.Map;

public class ProductsRepositoryWithShortDescriptionTestImpl implements ProductsRepository {

    private final ProductsRepository productsRepository;
    private final ProductProducer productProducer;

    public ProductsRepositoryWithShortDescriptionTestImpl(ProductsRepository productsRepository, ProductProducer productProducer) {
        this.productsRepository = productsRepository;
        this.productProducer = productProducer;
    }

    @Override
    public Products readProducts() throws RepositoryException {
        Products products = productsRepository.readProducts();

        Map<String, String> shortDescForProducts = new HashMap<>() {{
            put("id_1", "Sh desc 1");
            put("id_2", "Sh desc 2");
            put("id_6", "Sh desc 6");
            put("id_7", "Sh desc 7");}};

        shortDescForProducts.forEach((id, shortDesc) -> {
            Product product = products.findProductById(id);
            if(product != null)
                products.decorateProduct(id, productProducer.getProductWithShortDescriptionInstance(product, shortDesc));
        });
        return products;
    }
}
