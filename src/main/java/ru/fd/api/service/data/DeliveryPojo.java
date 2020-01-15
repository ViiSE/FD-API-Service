/*
 *  Copyright 2019 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
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

@ApiModel(value = "Delivery", description = "Доставка заказа")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DeliveryPojo {

    @ApiModelProperty(notes = "Тип доставки. Возможные значения:\n" +
            "<b>0</b> - самовывоз,\n" +
            "<b>1</b> - доставка на дом", position = 1, required = true)
    private final short type;
    @ApiModelProperty(notes = "GID города доставки", position = 2, required = true)
    private final String cityId;
    @ApiModelProperty(notes = "Адрес доставки (для типа <b>1</b>)", position = 3, required = true)
    private final String address;

    @ApiModelProperty(notes = "GID подразделения доставки (для типа <b>0</b>)", position = 4, required = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String departmentId;

    @ApiModelProperty(notes = "ID периода времени доставки заказа (для типа <b>1</b>). Возможные значения:\n" +
            "<b>0</b> - с 9 до 14,\n" +
            "<b>1</b> - с 14 до 19", position = 5, required = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Short deliveryTimeId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(example = "2020-01-13", notes = "Дата доставки (для типа <b>1</b>)", position = 6, required = true)
    private LocalDate deliveryDate;

    @JsonCreator
    public DeliveryPojo(
            @JsonProperty("type") short type,
            @JsonProperty("city_id") String cityId,
            @JsonProperty("address") String address) {
        this.type = type;
        this.cityId = cityId;
        this.address = address;
    }

    public short getType() {
        return type;
    }

    public String getCityId() {
        return cityId == null ? "" : cityId;
    }

    public String getAddress() {
        return address == null ? "" : address;
    }

    public String getDepartmentId() {
        return departmentId == null ? "" : departmentId;
    }

    public Short getDeliveryTimeId() {
        return deliveryTimeId;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate == null ? LocalDate.MIN : deliveryDate;
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
