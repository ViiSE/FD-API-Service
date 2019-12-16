package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel("Prices")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PricesPojo {

    private final List<PricePojo> prices;

    @JsonCreator
    public PricesPojo(List<PricePojo> prices) {
        this.prices = prices;
    }

    public List<PricePojo> getPrices() {
        return prices;
    }
}
