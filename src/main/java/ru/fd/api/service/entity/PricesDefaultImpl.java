package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.PricePojo;
import ru.fd.api.service.data.PricesPojo;

import java.util.List;
import java.util.stream.Collectors;

@Component("pricesDefault")
@Scope("prototype")
public class PricesDefaultImpl implements Prices {

    private final List<Price> prices;

    public PricesDefaultImpl(List<Price> prices) {
        this.prices = prices;
    }

    @Override
    public Object formForSend() {
        List<PricePojo> pricePojos = prices.stream()
                .map(price -> (PricePojo) price.formForSend())
                .collect(Collectors.toList());
        return new PricesPojo(pricePojos);
    }
}
