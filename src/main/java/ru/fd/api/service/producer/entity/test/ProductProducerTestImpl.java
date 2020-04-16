/*
 * Copyright 2020 ViiSE
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

package ru.fd.api.service.producer.entity.test;

import ru.fd.api.service.entity.*;
import ru.fd.api.service.producer.entity.ProductProducer;

public class ProductProducerTestImpl implements ProductProducer {

    @Override
    public Product getProductInstance(
            String id, String categoryId, String vendorId, String unitId, String name,
            short tax, String articul, String code) {
        return new ProductImpl(id, categoryId, vendorId, unitId, name, tax, articul, code);
    }

    @Override
    public Product getProductSimpleInstance(Product product, int key) {
        return new ProductSimpleImpl(product, key);
    }

    @Override
    public Product getProductWithAttributesInstance(Product product, Attributes attributes) {
        return new ProductWithAttributesImpl(product, attributes);
    }

    @Override
    public Product getProductWithBalancesInstance(Product product, Balances balances) {
        return new ProductWithBalancesImpl(product, balances);
    }

    @Override
    public Product getProductWithPricesInstance(Product product, Prices prices) {
        return new ProductWithPricesImpl(product, prices);
    }

    @Override
    public Product getProductWithStatusesInstance(Product product, Statuses statuses) {
        return new ProductWithStatusesImpl(product, statuses);
    }

    @Override
    public Product getProductWithShortDescriptionInstance(Product product, String shortDescription) {
        return new ProductWithShortDescriptionImpl(product, shortDescription);
    }

    @Override
    public Product getProductWithFullDescriptionInstance(Product product, String fullDescription) {
        return new ProductWithFullDescriptionImpl(product, fullDescription);
    }

    @Override
    public Product getProductWithChangedBalancesInstance(String id, Balances balances) {
        return new ProductWithChangedBalancesImpl(id, balances);
    }

    @Override
    public Product getOrderProductSimpleInstance(String id, int quantity, float sumPrice) {
        return new OrderProductSimpleImpl(id, quantity, sumPrice);
    }
}
