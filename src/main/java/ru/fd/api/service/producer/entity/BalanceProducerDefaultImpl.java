package ru.fd.api.service.producer.entity;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.entity.Balance;

@Service("balanceProducerDefault")
public class BalanceProducerDefaultImpl implements BalanceProducer {

    private final ApplicationContext ctx;

    public BalanceProducerDefaultImpl(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public Balance getBalanceDefaultInstance(String departmentId, int quantity) {
        return (Balance) ctx.getBean("balanceDefault", departmentId, quantity);
    }
}
