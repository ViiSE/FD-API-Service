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
import ru.fd.api.service.data.OrderPojo;
import ru.fd.api.service.data.ProductsOrderPojo;

@Component("orderWithProducts")
@Scope("prototype")
public class OrderWithProductsImpl implements Order {

    private final Order order;
    private final Products products;

    public OrderWithProductsImpl(Order order, Products products) {
        this.order = order;
        this.products = products;
    }

    @Override
    public Object formForSend() {
        OrderPojo orderPojo = (OrderPojo) order.formForSend();
        ProductsOrderPojo productsOrderPojo = (ProductsOrderPojo) products.formForSend();
        orderPojo.setProducts(productsOrderPojo.getProducts());
        return orderPojo;
    }

    @Override
    public long id() {
        return order.id();
    }

    @Override
    public short status() {
        return order.status();
    }
}
