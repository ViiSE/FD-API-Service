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

@ApiModel(value = "Products (with changed balances)", description = "Товары с измененными остатками")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductsChangedBalancesPojo {

    @ApiModelProperty(notes = "Список товаров", position = 1)
    private final List<ProductChangedBalancesPojo> products;

    @JsonCreator
    public ProductsChangedBalancesPojo(@JsonProperty("products") List<ProductChangedBalancesPojo> products) {
        this.products = products;
    }

    public List<ProductChangedBalancesPojo> getProducts() {
        return products;
    }
}
