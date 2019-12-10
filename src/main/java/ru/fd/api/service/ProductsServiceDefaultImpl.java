/*
 *  Copyright 2019 ViiSE.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package ru.fd.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fd.api.service.producer.creator.ProductsCreatorProducer;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.repository.ProductsRepositoryProducer;
import ru.fd.api.service.producer.repository.processor.ProductsRepositoryProcessorsProducer;

@Service("productsServiceDefault")
public class ProductsServiceDefaultImpl implements ProductsService {

    @Autowired private ProductsCreatorProducer productsCrProducer;
    @Autowired private ProductsRepositoryProcessorsProducer productsRepoProsProducer;
    @Autowired private ProductsRepositoryProducer productsRepoProducer;
    @Autowired private ProductProducer productProducer;

    @Override
    public ProductsCreatorProducer productsCreatorProducer() {
        return productsCrProducer;
    }

    @Override
    public ProductsRepositoryProcessorsProducer productsRepositoryProcessorsProducer() {
        return productsRepoProsProducer;
    }

    @Override
    public ProductsRepositoryProducer productsRepositoryProducer() {
        return productsRepoProducer;
    }

    @Override
    public ProductProducer productProducer() {
        return productProducer;
    }
}
