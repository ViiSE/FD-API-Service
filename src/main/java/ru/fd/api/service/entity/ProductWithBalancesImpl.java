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

package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.BalancesPojo;
import ru.fd.api.service.data.ProductPojo;

@Component("productWithBalances")
@Scope("prototype")
public class ProductWithBalancesImpl implements Product {

    private final Product product;
    private final Balances balances;

    public ProductWithBalancesImpl(Product product, Balances balances) {
        this.product = product;
        this.balances = balances;
    }

    @Override
    public String id() {
        return product.id();
    }

    @Override
    public int key() {
        return product.key();
    }

    @Override
    public Object formForSend() {
        BalancesPojo balancesPojo = (BalancesPojo) balances.formForSend();
        ProductPojo productPojo = (ProductPojo) product.formForSend();
        productPojo.setBalances(balancesPojo);
        return productPojo;
    }
}
