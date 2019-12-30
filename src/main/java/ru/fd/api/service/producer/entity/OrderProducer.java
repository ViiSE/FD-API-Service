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

package ru.fd.api.service.producer.entity;

import ru.fd.api.service.data.CustomerPojo;
import ru.fd.api.service.data.DeliveryPojo;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.entity.Products;

import java.time.LocalDateTime;

public interface OrderProducer {
    Order getOrderSimpleInstance(
            long id,
            String city,
            CustomerPojo customer,
            DeliveryPojo delivery,
            short payTypeId,
            LocalDateTime dateTime);
    Order getOrderWithCommentInstance(Order order, String comment);
    Order getOrderWithProductsInstance(Order order, Products products);
}
