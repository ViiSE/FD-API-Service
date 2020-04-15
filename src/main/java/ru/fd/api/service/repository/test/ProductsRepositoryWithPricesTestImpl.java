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

import ru.fd.api.service.entity.PriceImpl;
import ru.fd.api.service.entity.Prices;
import ru.fd.api.service.entity.PricesImpl;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.process.test.PsPricesTestImpl;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.ProductsRepository;

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
    public Products read() throws RepositoryException {
        Products products = productsRepository.read();

        Prices prices1 = new PsPricesTestImpl().answer(null);
        Prices prices2 = new PricesImpl(new ArrayList<>() {{
            add(new PriceImpl("dep_id_55555", 60.50f));
            add(new PriceImpl("dep_id_66665", 140.45f));
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
                                        new PricesImpl(new ArrayList<>())))));

        return products;
    }
}
