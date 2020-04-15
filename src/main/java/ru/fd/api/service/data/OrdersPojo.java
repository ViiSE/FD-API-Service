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

package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

// TODO: 23.01.2020 FUTURE
//@ApiModel(value = "Orders", description = "Заказы")
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OrdersPojo {

//    @ApiModelProperty(notes = "Список заказов", position = 1)
    private final List<OrderPojo> orders;

    @JsonCreator
    public OrdersPojo(
//            @JsonProperty("orders")
            List<OrderPojo> orders) {
        this.orders = orders;
    }

    public List<OrderPojo> getOrders() {
        return orders;
    }
}
