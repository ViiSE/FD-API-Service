package ru.fd.api.service.producer.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.entity.Balance;

@Service("balanceProducerDefault")
public class BalanceProducerDefaultImpl implements BalanceProducer {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public Balance getBalanceDefaultInstance(String departmentId, int quantity) {
        return (Balance) ctx.getBean("balancesDefault", departmentId, quantity);
    }
}
