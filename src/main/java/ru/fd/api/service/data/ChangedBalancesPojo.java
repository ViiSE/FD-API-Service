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

@ApiModel(value = "ChangedBalances", description = "Изменения остатков товаров")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ChangedBalancesPojo {

    @ApiModelProperty(notes = "Список изменений остатков товаров", position = 1)
    private final List<ChangedBalancePojo> changedBalances;

    @JsonCreator
    public ChangedBalancesPojo(@JsonProperty("changed_balances") List<ChangedBalancePojo> changedBalances) {
        this.changedBalances = changedBalances;
    }

    public List<ChangedBalancePojo> getChangedBalances() {
        return changedBalances;
    }
}
