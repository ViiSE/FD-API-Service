package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "Balances", description = "Остатки товара")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BalancesPojo {

    @ApiModelProperty(notes = "Список остатков товаров", position = 1)
    private final List<BalancePojo> balances;

    @JsonCreator
    public BalancesPojo(@JsonProperty("balances") List<BalancePojo> balances) {
        this.balances = balances;
    }

    public List<BalancePojo> getBalances() {
        return balances;
    }
}
