/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.BalancesPojo;
import ru.fd.api.service.data.ProductChangedBalancesPojo;

@Component("productWithChangedBalances")
@Scope("prototype")
public class ProductWithChangedBalancesImpl implements Product {

    private final String id;
    private final Balances balances;

    public ProductWithChangedBalancesImpl(String id, Balances balances) {
        this.id = id;
        this.balances = balances;
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public int key() {
        return 0;
    }

    @Override
    public Object formForSend() {
        return new ProductChangedBalancesPojo(id, (BalancesPojo) balances.formForSend());
    }
}
