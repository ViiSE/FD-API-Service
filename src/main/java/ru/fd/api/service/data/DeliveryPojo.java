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

package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;

import java.time.LocalDate;

@ApiModel("Delivery")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DeliveryPojo {

    private final short type;
    private final String cityId;
    private final String address;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String departmentId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private short deliveryTimeId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-DD")
    private LocalDate deliveryDate;

    @JsonCreator
    public DeliveryPojo(
            @JsonProperty("type") short type,
            @JsonProperty("city") String cityId,
            @JsonProperty("address") String address) {
        this.type = type;
        this.cityId = cityId;
        this.address = address;
    }

    public short getType() {
        return type;
    }

    public String getCityId() {
        return cityId;
    }

    public String getAddress() {
        return address;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public short getDeliveryTimeId() {
        return deliveryTimeId;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public void setDeliveryTime(short deliveryTimeId) {
        this.deliveryTimeId = deliveryTimeId;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
