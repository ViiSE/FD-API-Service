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

import ru.fd.api.service.entity.OrderProductSimpleImpl;
import ru.fd.api.service.entity.OrderProductsImpl;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.process.Process;

import java.util.ArrayList;

public class PsOrderProductsTestImpl implements Process<Products, Void> {

    public Products answer(Void v) {
        return new OrderProductsImpl(new ArrayList<>() {{
            add(new OrderProductSimpleImpl("id1", 5, 10.00f));
            add(new OrderProductSimpleImpl("id2", 10, 12.50f));
        }});
    }
}
