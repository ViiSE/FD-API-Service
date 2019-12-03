package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.BalancePojo;
import ru.fd.api.service.data.BalancesPojo;

import java.util.List;
import java.util.stream.Collectors;

@Component("productBalancesDefault")
@Scope("prototype")
public class ProductBalancesDefaultImpl implements Balances {

    private final List<Balance> balances;

    public ProductBalancesDefaultImpl(List<Balance> balances) {
        this.balances = balances;
    }

    @Override
    public Object formForSend() {
        List<BalancePojo> balancePojos = balances.stream()
                .map(balance -> (BalancePojo) balance.formForSend())
                .collect(Collectors.toList());
        return new BalancesPojo(balancePojos);
    }
}
