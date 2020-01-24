package test.repository;

import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.ProductsRepository;

import java.util.HashMap;
import java.util.Map;

public class ProductsRepositoryWithFullDescriptionTestImpl implements ProductsRepository {

    private final ProductsRepository productsRepository;
    private final ProductProducer productProducer;

    public ProductsRepositoryWithFullDescriptionTestImpl(ProductsRepository productsRepository, ProductProducer productProducer) {
        this.productsRepository = productsRepository;
        this.productProducer = productProducer;
    }

    @Override
    public Products read() throws RepositoryException {
        Products products = productsRepository.read();

        Map<String, String> fullDescForProducts = new HashMap<>() {{
            put("id_1", "Full Description 1");
            put("id_2", "Full Description 2");
            put("id_8", "Full Description 8");
            put("id_9", "Full Description 9");}};

        products.forEach(product -> products.decorateProduct(
                product.id(),
                productProducer.getProductWithFullDescriptionInstance(
                        product,
                        fullDescForProducts.getOrDefault(product.id(), ""))));

        return products;
    }
}
