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
import ru.fd.api.service.data.PriceOfferPojo;

@Component("priceOfferWithDepartmentId")
@Scope("prototype")
public class PriceOfferWithDepartmentIdImpl implements Price {

    private final Price price;
    private final String departmentId;

    public PriceOfferWithDepartmentIdImpl(Price price, String departmentId) {
        this.price = price;
        this.departmentId = departmentId;
    }

    @Override
    public Object formForSend() {
        PriceOfferPojo priceOfferPojo = (PriceOfferPojo) price.formForSend();
        priceOfferPojo.setDepartmentId(departmentId);

        return priceOfferPojo;
    }
}
