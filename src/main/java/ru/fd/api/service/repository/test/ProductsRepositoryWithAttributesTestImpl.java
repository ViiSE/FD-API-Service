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

import ru.fd.api.service.entity.Attributes;
import ru.fd.api.service.entity.ProductAttributeImpl;
import ru.fd.api.service.entity.ProductAttributesImpl;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.process.test.PsAttributesTestImpl;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.ProductsRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductsRepositoryWithAttributesTestImpl implements ProductsRepository {

    private final ProductsRepository productsRepository;
    private final ProductProducer productProducer;

    public ProductsRepositoryWithAttributesTestImpl(ProductsRepository productsRepository, ProductProducer productProducer) {
        this.productsRepository = productsRepository;
        this.productProducer = productProducer;
    }

    @Override
    public Products read() throws RepositoryException {
        Products products = productsRepository.read();

        Attributes attributes1 = new PsAttributesTestImpl().answer(null);
        Attributes attributes2 = new ProductAttributesImpl(new ArrayList<>() {{
            add(new ProductAttributeImpl("attr_1", "value attr 1"));
            add(new ProductAttributeImpl("attr_2", "value attr 2"));
            add(new ProductAttributeImpl("attr_3", "value attr 3"));
        }});


        Map<String, Attributes> attrForProducts = new HashMap<>() {{
            put("id_1", attributes1);
            put("id_2", attributes2); }};

        products.forEach(product ->
            products.decorateProduct(
                    product.id(),
                    productProducer.getProductWithAttributesInstance(
                            product,
                            attrForProducts.getOrDefault(
                                    product.id(),
                                    new ProductAttributesImpl(new ArrayList<>())))));

        return products;
    }
}
