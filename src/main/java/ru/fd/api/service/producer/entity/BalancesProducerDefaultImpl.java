/*
 *  Copyright 2019 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.producer.entity;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.entity.Balance;
import ru.fd.api.service.entity.Balances;

import java.util.List;

@Service("balancesProducerDefault")
public class BalancesProducerDefaultImpl implements BalancesProducer {

    private final ApplicationContext ctx;

    public BalancesProducerDefaultImpl(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public Balances getBalancesDefaultInstance(List<Balance> balances) {
        return (Balances) ctx.getBean("balancesDefault", balances);
    }
}
