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
import ru.fd.api.service.data.DeliveryPojo;

import java.time.LocalDate;

@Component("deliveryWithDate")
@Scope("prototype")
public class DeliveryWithDateImpl implements Delivery {

    private final Delivery delivery;
    private final LocalDate deliveryDate;

    public DeliveryWithDateImpl(Delivery delivery, LocalDate deliveryDate) {
        this.delivery = delivery;
        this.deliveryDate = deliveryDate;
    }

    @Override
    public Object formForSend() {
        DeliveryPojo deliveryPojo = (DeliveryPojo) delivery.formForSend();
        deliveryPojo.setDeliveryDate(deliveryDate);
        return deliveryPojo;
    }
}
