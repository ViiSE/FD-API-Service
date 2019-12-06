package test.repository;

import ru.fd.api.service.entity.*;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.ProductsRepository;
import test.creator.BalancesCreatorTestImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductsRepositoryWithBalancesTestImpl implements ProductsRepository {

    private final ProductsRepository productsRepository;
    private final ProductProducer productProducer;

    public ProductsRepositoryWithBalancesTestImpl(ProductsRepository productsRepository, ProductProducer productProducer) {
        this.productsRepository = productsRepository;
        this.productProducer = productProducer;
    }

    @Override
    public Products readProducts() throws RepositoryException {
        Products products = productsRepository.readProducts();

        Balances balances1 = new BalancesCreatorTestImpl().createBalances();
        Balances balances2 = new BalancesDefaultImpl(new ArrayList<>() {{
            add(new BalanceDefaultImpl("dep_3", 60));
            add(new BalanceDefaultImpl("dep_4", 70));
            add(new BalanceDefaultImpl("dep_5", 80));
        }});


        Map<String, Balances> balancesForProducts = new HashMap<>() {{
            put("id_1", balances1);
            put("id_2", balances2); }};

        products.forEach(product ->
                products.decorateProduct(
                        product.id(),
                        productProducer.getProductWithBalancesInstance(
                                product,
                                balancesForProducts.getOrDefault(product.id(), new BalancesDefaultImpl(new ArrayList<>())))));

        return products;
    }
}
