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

import java.util.List;

@ApiModel(value = "Product (with changed balance)", description = "Товар с измененными остатками")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductChangedBalancesPojo {

    @ApiModelProperty(notes = "GID товара", position = 1)
    private final String id;
    @ApiModelProperty(notes = "Список измененных остатков товара", position = 2)
    private final BalancesPojo balances;

    @JsonCreator
    public ProductChangedBalancesPojo(
            @JsonProperty("id") String id,
            @JsonProperty("balances") BalancesPojo balances) {
        this.id = id;
        this.balances = balances;
    }

    public String getId() {
        return id;
    }

    public List<BalancePojo> getBalances() {
        return balances.getBalances();
    }
}
