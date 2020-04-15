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

import ru.fd.api.service.entity.Product;
import ru.fd.api.service.entity.ProductImpl;
import ru.fd.api.service.process.Process;

public class PsProductTestImpl implements Process<Product, Void> {

    @Override
    public Product answer(Void v) {
        String id = "id_1";
        String categoryId = "category_1";
        String vendorId = "vendor_1";
        String unitId = "unit_1";
        String name = "Item_1";
        short tax = 20;
        String articul = "art_1";
        String code = "code_1";

        return new ProductImpl(id, categoryId, vendorId, unitId, name, tax, articul, code);
    }
}
