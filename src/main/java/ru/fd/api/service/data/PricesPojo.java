package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductPricesPojo {

    private final List<ProductPricePojo> prices;

    @JsonCreator
    public ProductPricesPojo(List<ProductPricePojo> prices) {
        this.prices = prices;
    }

    public List<ProductPricePojo> getPrices() {
        return prices;
    }
}
