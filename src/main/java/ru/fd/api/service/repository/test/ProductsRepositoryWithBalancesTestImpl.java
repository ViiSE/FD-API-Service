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

package ru.fd.api.service.repository.test;

import ru.fd.api.service.entity.BalanceImpl;
import ru.fd.api.service.entity.Balances;
import ru.fd.api.service.entity.BalancesImpl;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.process.test.PsBalancesTestImpl;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.ProductsRepository;

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
    public Products read() throws RepositoryException {
        Products products = productsRepository.read();

        Balances balances1 = new PsBalancesTestImpl().answer(null);
        Balances balances2 = new BalancesImpl(new ArrayList<>() {{
            add(new BalanceImpl("dep_3", 60));
            add(new BalanceImpl("dep_4", 70));
            add(new BalanceImpl("dep_5", 80));
        }});


        Map<String, Balances> balancesForProducts = new HashMap<>() {{
            put("id_1", balances1);
            put("id_2", balances2); }};

        products.forEach(product ->
                products.decorateProduct(
                        product.id(),
                        productProducer.getProductWithBalancesInstance(
                                product,
                                balancesForProducts.getOrDefault(product.id(), new BalancesImpl(new ArrayList<>())))));

        return products;
    }
}
