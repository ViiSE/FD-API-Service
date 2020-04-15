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

package ru.fd.api.service.process;

import org.springframework.stereotype.Service;
import ru.fd.api.service.constant.AdditionalProducts;
import ru.fd.api.service.entity.Sendable;

import java.util.HashMap;
import java.util.Map;

@Service("psProductAdditional")
public class PsProductAdditionalImpl implements Processes<Sendable, Void> {

    private final Map<String, Process<Sendable, Void>> procMap = new HashMap<>();

    public PsProductAdditionalImpl(PsProducer producer) {
        procMap.put(AdditionalProducts.ATTRIBUTE_GROUPS, producer.psAttrGroupsInstance());
        procMap.put(AdditionalProducts.ATTRIBUTES, producer.psAttrsInstance());
        procMap.put(AdditionalProducts.CATEGORIES, producer.psCategoriesInstance());
        procMap.put(AdditionalProducts.STATUSES, producer.psStatusesInstance());
        procMap.put(AdditionalProducts.UNITS, producer.psUnitsInstance());
    }

    @Override
    public Process<Sendable, Void> process(String key) {
        return procMap.get(key);
    }
}
