/*
 * Copyright 2019 ViiSE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.fd.api.service.repository.decorative;

import org.springframework.stereotype.Service;
import ru.fd.api.service.constant.RepositoriesProducts;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.repository.ProductsRepositoryDecorative;

import java.util.HashMap;
import java.util.Map;

@Service("repositoriesProductsDecorative")
public class RepositoriesProductsDecorativeImpl implements Repositories<ProductsRepositoryDecorative<Products>> {

    private final Map<String, ProductsRepositoryDecorative<Products>> reposMap = new HashMap<>();

    public RepositoriesProductsDecorativeImpl(ProductsRepositoryDecorativeProducer producer) {
        reposMap.put(RepositoriesProducts.ATTRIBUTES, producer.productsRepoWithAttrInstance());
        reposMap.put(RepositoriesProducts.BALANCES, producer.productsRepoWithBalancesInstance());
        reposMap.put(RepositoriesProducts.PRICES, producer.productsRepoWithPricesInstance());
        reposMap.put(RepositoriesProducts.STATUSES, producer.productsRepoWithStatsInstance());
        reposMap.put(RepositoriesProducts.DESCRIPTIONS, producer.productsRepoWithDescriptionInstance());
    }

    @Override
    public ProductsRepositoryDecorative<Products> repo(String key) {
        return reposMap.get(key);
    }
}
