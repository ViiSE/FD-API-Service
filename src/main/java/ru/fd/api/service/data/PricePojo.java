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

@ApiModel(value = "Price", description = "Цена товара")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PricePojo {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(notes = "GID товара", position = 1)
    private String productId;
    @ApiModelProperty(notes = "GID подразделения", position = 2)
    private final String departmentId;
    @ApiModelProperty(notes = "Значение цены", position = 3)
    private final float value;

    @JsonCreator
    public PricePojo(
            @JsonProperty("department_id") String departmentId,
            @JsonProperty("value") float value) {
        this.departmentId = departmentId;
        this.value = value;
    }

    public String getProductId() {
        return productId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public float getValue() {
        return value;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
