package test.repository;

import ru.fd.api.service.entity.Products;
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
    public Products read() throws RepositoryException {
        Products products = productsRepository.read();

        Map<String, String> shortDescForProducts = new HashMap<>() {{
            put("id_1", "Sh desc 1");
            put("id_2", "Sh desc 2");
            put("id_6", "Sh desc 6");
            put("id_7", "Sh desc 7");}};

        products.forEach(product ->
                products.decorateProduct(
                        product.id(),
                        productProducer.getProductWithShortDescriptionInstance(
                                product,
                                shortDescForProducts.getOrDefault(product.id(), ""))));

        return products;
    }
}
