/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ChangedBalance", description = "Изменения остатков товара")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ChangedBalancePojo {

    @ApiModelProperty(notes = "GID товара", position = 1)
    private final String id;
    @ApiModelProperty(notes = "Измененное (обновленное) количество товара", position = 2)
    private final int quantity;

    @JsonCreator
    public ChangedBalancePojo(
            @JsonProperty("id") String id,
            @JsonProperty("quantity") int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getId() {
        return id;
    }
}
