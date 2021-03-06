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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.ZonedDateTime;
import java.util.List;

@ApiModel(value = "Products (with changed balances)", description = "Товары с измененными остатками")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductsChangedBalancesPojo {

    @ApiModelProperty(notes = "Время и дата выгрузки товара в формате ISO 8601", position = 1)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private ZonedDateTime from;

    @ApiModelProperty(notes = "Список товаров", position = 2)
    private final List<ProductChangedBalancesPojo> products;

    @JsonCreator
    public ProductsChangedBalancesPojo(@JsonProperty("products") List<ProductChangedBalancesPojo> products) {
        this.products = products;
    }

    public List<ProductChangedBalancesPojo> getProducts() {
        return products;
    }

    public void setFrom(ZonedDateTime from) {
        this.from = from;
    }

    public ZonedDateTime getFrom() {
        return from;
    }
}
