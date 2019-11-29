package ru.fd.api.service.producer.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.entity.Balance;
import ru.fd.api.service.entity.Balances;

import java.util.List;

@Service("balancesProducerDefault")
public class BalancesProducerDefaultImpl implements BalancesProducer {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public Balances getBalancesDefaultInstance(List<Balance> balances) {
        return (Balances) ctx.getBean("balancesDefault", balances);
    }
}
