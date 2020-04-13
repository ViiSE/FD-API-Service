package ru.fd.api.service.repository.decorative;

import org.springframework.stereotype.Service;
import ru.fd.api.service.repository.ProductsRepositoryDecorative;

import java.util.HashMap;
import java.util.Map;

@Service("repositoriesProductsDecorative")
public class RepositoriesProductsDecorativeImpl implements Repositories<ProductsRepositoryDecorative> {

    private final Map<String, ProductsRepositoryDecorative> reposMap = new HashMap<>();

    public RepositoriesProductsDecorativeImpl(ProductsRepositoryDecorativeProducer producer) {
        reposMap.put("attributes", producer.productsRepoWithAttrInstance());
        reposMap.put("balances", producer.productsRepoWithBalancesInstance());
        reposMap.put("prices", producer.productsRepoWithPricesInstance());
        reposMap.put("statuses", producer.productsRepoWithStatsInstance());
        reposMap.put("short_description", producer.productsRepoWithShortDescInstance());
        reposMap.put("full_description", producer.productsRepoWithFullDescInstance());
    }

    @Override
    public ProductsRepositoryDecorative repo(String key) {
        return reposMap.get(key);
    }
}
