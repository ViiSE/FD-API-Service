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

import org.springframework.stereotype.Component;
import ru.fd.api.service.data.ProductPojo;
import ru.fd.api.service.data.ProductsPojo;

import java.util.List;
import java.util.stream.Collectors;

@Component("orderProductsDefault")
public class OrderProductsDefaultImpl implements OrderProducts {

    private final List<Product> products;

    public OrderProductsDefaultImpl(List<Product> products) {
        this.products = products;
    }

    @Override
    public Object formForSend() {
        List<ProductPojo> productPojos = products.stream()
                .map(product -> (ProductPojo) product.formForSend())
                .collect(Collectors.toList());
        return new ProductsPojo(productPojos);
    }
}
