/*
 * Copyright 2020 ViiSE
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

@ApiModel(value = "PriceOffer", description = "Цена товара, участвующего в акции")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PriceOfferPojo {

    @ApiModelProperty(notes = "GID подразделения", position = 1)
    private String departmentId;
    @ApiModelProperty(notes = "Цена до акции", position = 2)
    private final float originalValue;
    @ApiModelProperty(notes = "Цена после акции", position = 3)
    private final float offerValue;

    @JsonCreator
    public PriceOfferPojo(
            @JsonProperty("original_value") float originalValue,
            @JsonProperty("offer_value") float offerValue) {
        this.originalValue = originalValue;
        this.offerValue = offerValue;
    }

    public float getOfferValue() {
        return offerValue;
    }

    public float getOriginalValue() {
        return originalValue;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentId() {
        return departmentId;
    }
}
