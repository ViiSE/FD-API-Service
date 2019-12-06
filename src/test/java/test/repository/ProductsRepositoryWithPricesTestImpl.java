package test.repository;

import ru.fd.api.service.entity.PriceDefaultImpl;
import ru.fd.api.service.entity.Prices;
import ru.fd.api.service.entity.PricesDefaultImpl;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.ProductsRepository;
import test.creator.PricesCreatorTestImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductsRepositoryWithPricesTestImpl implements ProductsRepository {

    private final ProductsRepository productsRepository;
    private final ProductProducer productProducer;

    public ProductsRepositoryWithPricesTestImpl(ProductsRepository productsRepository, ProductProducer productProducer) {
        this.productsRepository = productsRepository;
        this.productProducer = productProducer;
    }

    @Override
    public Products readProducts() throws RepositoryException {
        Products products = productsRepository.readProducts();

        Prices prices1 = new PricesCreatorTestImpl().createPrices();
        Prices prices2 = new PricesDefaultImpl(new ArrayList<>() {{
            add(new PriceDefaultImpl("dep_id_55555", 60.50f));
            add(new PriceDefaultImpl("dep_id_66665", 140.45f));
        }});

        Map<String, Prices> pricesForProducts = new HashMap<>() {{
            put("id_1", prices1);
            put("id_2", prices2); }};

        products.forEach(product ->
                products.decorateProduct(
                        product.id(),
                        productProducer.getProductWithPricesInstance(
                                product,
                                pricesForProducts.getOrDefault(
                                        product.id(),
                                        new PricesDefaultImpl(new ArrayList<>())))));

        return products;
    }
}
