package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "Prices", description = "Цены товара")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PricesPojo {

    @ApiModelProperty(notes = "Список цен товара", position = 1)
    private final List<PricePojo> prices;

    @JsonCreator
    public PricesPojo(List<PricePojo> prices) {
        this.prices = prices;
    }

    public List<PricePojo> getPrices() {
        return prices;
    }
}
