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

import ru.fd.api.service.entity.ProductStatusImpl;
import ru.fd.api.service.entity.ProductStatusesImpl;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.entity.Statuses;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.process.test.PsStatusesTestImpl;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.ProductsRepository;

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
    public Products read() throws RepositoryException {
        Products products = productsRepository.read();

        Statuses statuses1 = new PsStatusesTestImpl().answer(null);
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

        products.forEach(product ->
                products.decorateProduct(
                        product.id(),
                        productProducer.getProductWithStatusesInstance(
                                product,
                                statusesForProducts.getOrDefault(
                                        product.id(),
                                        new ProductStatusesImpl(new ArrayList<>())))));

        return products;
    }
}
