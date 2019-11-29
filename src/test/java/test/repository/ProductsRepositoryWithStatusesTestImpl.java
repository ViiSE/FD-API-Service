package test.repository;

import ru.fd.api.service.entity.*;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.ProductsRepository;
import test.creator.ProductStatusesCreatorTestImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductsRepositoryWithStatusesTestImpl implements ProductsRepository {

    private final ProductsRepository productsRepository;
    private final ProductProducer productProducer;

    public ProductsRepositoryWithStatusesTestImpl(ProductsRepository productsRepository, ProductProducer productProducer) {
        this.productsRepository = productsRepository;
        this.productProducer = productProducer;
    }

    @Override
    public Products readProducts() throws RepositoryException {
        Products products = productsRepository.readProducts();

        Statuses statuses1 = new ProductStatusesCreatorTestImpl().createStatuses();
        Statuses statuses2 = new ProductStatusesImpl(new ArrayList<>() {{
            add(new ProductStatusImpl("dep_333", "status 333"));
            add(new ProductStatusImpl("dep_444", "status 444"));
            add(new ProductStatusImpl("dep_555", "status 555"));
            add(new ProductStatusImpl("dep_666", "status 666"));
        }});


        Map<String, Statuses> statusesForProducts = new HashMap<>() {{
            put("id_1", statuses1);
            put("id_2", statuses2);
            put("id_3", statuses2);
            put("id_4", statuses1); }};

        statusesForProducts.forEach((id, statuses) -> {
            Product product = products.findProductById(id);
            if(product != null)
                products.decorateProduct(id, productProducer.getProductWithStatusesInstance(product, statuses));
        });
        return products;
    }
}
