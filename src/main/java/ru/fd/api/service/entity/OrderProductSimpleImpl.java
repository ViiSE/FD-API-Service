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

package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.ProductOrderPojo;

@Component("orderProductSimple")
@Scope("prototype")
public class OrderProductSimpleImpl implements Product {

    private final String id;
    private final int quantity;
    private final float sumPrice;

    public OrderProductSimpleImpl(String id, int quantity, float sumPrice) {
        this.id = id;
        this.quantity = quantity;
        this.sumPrice = sumPrice;
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
        return new ProductOrderPojo(id, quantity, sumPrice);
    }
}
