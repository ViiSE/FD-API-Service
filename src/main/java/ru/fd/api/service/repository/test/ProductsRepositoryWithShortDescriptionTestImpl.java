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
