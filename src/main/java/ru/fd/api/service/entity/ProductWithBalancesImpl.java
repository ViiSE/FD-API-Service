package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.BalancesPojo;
import ru.fd.api.service.data.ProductPojo;

@Component("productWithBalances")
@Scope("prototype")
public class ProductWithBalancesImpl implements Product {

    private final Product product;
    private final Balances balances;

    public ProductWithBalancesImpl(Product product, Balances balances) {
        this.product = product;
        this.balances = balances;
    }

    @Override
    public String id() {
        return product.id();
    }

    @Override
    public Object formForSend() {
        BalancesPojo balancesPojo = (BalancesPojo) balances.formForSend();
        ProductPojo productPojo = (ProductPojo) product.formForSend();
        productPojo.setBalances(balancesPojo);
        return productPojo;
    }
}
