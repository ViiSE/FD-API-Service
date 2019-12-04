package test.repository;

import ru.fd.api.service.entity.Product;
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
    public Products readProducts() throws RepositoryException {
        Products products = productsRepository.readProducts();

        Map<String, String> fullDescForProducts = new HashMap<>() {{
            put("id_1", "Full Description 1");
            put("id_2", "Full Description 2");
            put("id_8", "Full Description 8");
            put("id_9", "Full Description 9");}};

        fullDescForProducts.forEach((id, fullDesc) -> {
            Product product = products.findProductById(id);
            if(product != null)
                products.decorateProduct(id, productProducer.getProductWithFullDescriptionInstance(product, fullDesc));
        });
        return products;
    }
}
