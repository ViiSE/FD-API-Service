package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.PricesPojo;
import ru.fd.api.service.data.ProductPojo;

@Component("productWithPrices")
@Scope("prototype")
public class ProductWithPricesImpl implements Product {

    private final Product product;
    private final Prices prices;

    public ProductWithPricesImpl(Product product, Prices prices) {
        this.product = product;
        this.prices = prices;
    }

    @Override
    public Object formForSend() {
        PricesPojo pricesPojo = (PricesPojo) prices.formForSend();
        ProductPojo productPojo = (ProductPojo) product.formForSend();
        productPojo.setPrices(pricesPojo);
        return productPojo;
    }
}
