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

package ru.fd.api.service.process.test;

import ru.fd.api.service.entity.*;
import ru.fd.api.service.process.Process;
import ru.fd.api.service.producer.entity.test.ProductProducerTestImpl;

import java.util.ArrayList;
import java.util.List;

public class PsProductsTestImpl implements Process<Products, Void> {

    @Override
    public Products answer(Void v) {
        List<Product> products = new ArrayList<>();

        for(int i = 1; i <= 10; i++)
            products.add(createProduct(i));

        return new ProductsImpl(new ProductProducerTestImpl(), products);
    }

    private Product createProduct(int id) {
        return new ProductSimpleImpl(new ProductImpl(
                "id_" + id,
                "category_" + id,
                "vendor_" + id,
                "unit_" + id,
                "Item_" + id,
                (short) 20,
                "art_" + id,
                "code_" + id),
                ++id);
    }
}
