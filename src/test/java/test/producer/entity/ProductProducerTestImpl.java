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

package test.producer.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.entity.*;
import ru.fd.api.service.producer.entity.ProductProducer;

@Service("productProducerDefault")
public class ProductProducerTestImpl implements ProductProducer {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public Product getProductSimpleInstance(
            String id, String categoryId, String vendorId, String unitId, String name,
            short tax, String articul, String code) {
        return new ProductSimpleImpl(id, categoryId, vendorId, unitId, name, tax, articul, code);
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
}
