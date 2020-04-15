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
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.Objects;

@ApiModel(value = "Delivery", description = "Доставка заказа")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DeliveryPojo {

    @ApiModelProperty(notes = "Тип доставки. Возможные значения:\n" +
            "<b>0</b> - самовывоз,\n" +
            "<b>1</b> - доставка на дом", position = 1, required = true)
    private final Short type;
    @ApiModelProperty(notes = "ID города доставки", position = 2, required = true)
    private final Integer cityId;
    @ApiModelProperty(notes = "Адрес доставки (для типа <b>1</b>)", position = 3, required = true)
    private final String address;

    @ApiModelProperty(notes = "GID подразделения доставки (для типа <b>0</b>)", position = 4, required = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String departmentId;

    @ApiModelProperty(notes = "ID периода времени доставки заказа (для типа <b>1</b>). Возможные значения:\n" +
            "<b>0</b> - с 9 до 14,\n" +
            "<b>1</b> - с 14 до 19", position = 5, required = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Short deliveryTimeId = -1;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(example = "2020-01-13", notes = "Дата доставки (для типа <b>1</b>)", position = 6, required = true)
    private LocalDate deliveryDate;

    @JsonCreator
    public DeliveryPojo(
            @JsonProperty("type") Short type,
            @JsonProperty("city_id") Integer cityId,
            @JsonProperty("address") String address) {
        this.type = Objects.requireNonNullElse(type, (short) -1);
        this.cityId = Objects.requireNonNullElse(cityId, -1);
        this.address = Objects.requireNonNullElse(address, "");
    }

    public Short getType() {
        return type == null ? -1 : type;
    }

    public Integer getCityId() {
        return cityId == null ? -1 : cityId;
    }

    public String getAddress() {
        return address == null ? "" : address;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public Short getDeliveryTimeId() {
        return deliveryTimeId;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate == null ? LocalDate.of(1, 1, 1) : deliveryDate;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public void setDeliveryTimeId(short deliveryTimeId) {
        this.deliveryTimeId = deliveryTimeId;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
