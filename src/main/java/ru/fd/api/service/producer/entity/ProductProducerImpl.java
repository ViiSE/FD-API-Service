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

package ru.fd.api.service.producer.entity;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.entity.*;

@Service("productProducer")
public class ProductProducerImpl implements ProductProducer {

    private final ApplicationContext ctx;

    public ProductProducerImpl(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public Product getProductInstance(String id, String categoryId, String vendorId, String unitId, String name, short tax, String articul, String code) {
        return (Product) ctx.getBean("product", id, categoryId, vendorId, unitId, name, tax, articul, code);
    }

    @Override
    public Product getProductSimpleInstance(Product product, int key) {
        return (Product) ctx.getBean("productSimple", product, key);
    }

    @Override
    public Product getProductWithAttributesInstance(Product product, Attributes attributes) {
        return (Product) ctx.getBean("productWithAttributes", product, attributes);
    }

    @Override
    public Product getProductWithBalancesInstance(Product product, Balances balances) {
        return (Product) ctx.getBean("productWithBalances", product, balances);
    }

    @Override
    public Product getProductWithPricesInstance(Product product, Prices prices) {
        return (Product) ctx.getBean("productWithPrices", product, prices);
    }

    @Override
    public Product getProductWithStatusesInstance(Product product, Statuses statuses) {
        return (Product) ctx.getBean("productWithStatuses", product, statuses);
    }

    @Override
    public Product getProductWithShortDescriptionInstance(Product product, String shortDescription) {
        return (Product) ctx.getBean("productWithShortDescription", product, shortDescription);
    }

    @Override
    public Product getProductWithFullDescriptionInstance(Product product, String fullDescription) {
        return (Product) ctx.getBean("productWithFullDescription", product, fullDescription);
    }

    @Override
    public Product getProductWithChangedBalancesInstance(String id, Balances balances) {
        return (Product) ctx.getBean("productWithChangedBalances", id, balances);
    }

    @Override
    public Product getOrderProductSimpleInstance(String id, int quantity) {
        return (Product) ctx.getBean("orderProductSimple", id, quantity);
    }
}
