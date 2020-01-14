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

    @ApiModelProperty(notes = "Тип доставки", position = 1)
    private final short type;
    @ApiModelProperty(notes = "ID города доставки", position = 2)
    private final String cityId;
    @ApiModelProperty(notes = "Адрес доставки", position = 3)
    private final String address;

    @ApiModelProperty(notes = "ID подразделения доставки", position = 4)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String departmentId;

    @ApiModelProperty(notes = "ID периода времени доставки заказа", position = 5)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Short deliveryTimeId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(example = "2020-01-13", notes = "Дата доставки", position = 6)
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
