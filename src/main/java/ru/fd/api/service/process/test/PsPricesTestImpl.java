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

import ru.fd.api.service.entity.Price;
import ru.fd.api.service.entity.PriceImpl;
import ru.fd.api.service.entity.Prices;
import ru.fd.api.service.entity.PricesImpl;
import ru.fd.api.service.process.Process;

import java.util.ArrayList;

public class PsPricesTestImpl implements Process<Prices, Void> {

    @Override
    public Prices answer(Void v) {
        Price price1 = new PriceImpl("dep_id_11111", 100.50f);
        Price price2 = new PriceImpl("dep_id_22222", 160.50f);
        return new PricesImpl(new ArrayList<>() {{ add(price1); add(price2); }});
    }
}
