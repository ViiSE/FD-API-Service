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
import ru.fd.api.service.data.ProductOfferPojo;

@Component("productOfferWithId")
@Scope("prototype")
public class ProductOfferWithIdImpl implements Product {

    private final String id;

    public ProductOfferWithIdImpl(String id) {
        this.id = id;
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public int key() {
        return 0;
    }

    @Override
    public Object formForSend() {
        ProductOfferPojo productPojo = new ProductOfferPojo();
        productPojo.setId(id);
        return productPojo;
    }
}
