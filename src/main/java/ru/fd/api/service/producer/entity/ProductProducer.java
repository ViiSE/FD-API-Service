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

import ru.fd.api.service.entity.*;

public interface ProductProducer {
    Product getProductInstance(
            String id, String categoryId, String vendorId, String unitId, String name,
            short tax, String articul, String code);
    Product getProductSimpleInstance(Product product, int key);
    Product getProductWithAttributesInstance(Product product, Attributes attributes);
    Product getProductWithBalancesInstance(Product product, Balances balances);
    Product getProductWithPricesInstance(Product product, Prices prices);
    Product getProductWithStatusesInstance(Product product, Statuses statuses);
    Product getProductWithShortDescriptionInstance(Product product, String shortDescription);
    Product getProductWithFullDescriptionInstance(Product product, String fullDescription);
    Product getProductWithChangedBalancesInstance(String id, Balances balances);
    Product getOrderProductSimpleInstance(String id, int quantity, float sumPrice);

    Product getProductOfferWithIdInstance(String id);
    Product getProductOfferWithOfferIdInstance(Product product, long offerId);
    Product getProductOfferWithOfferPriceInstance(Product product, Price price);
}
