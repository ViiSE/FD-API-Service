package ru.fd.api.service.producer.entity;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.entity.Price;

@Service("priceProducerDefault")
public class PriceProducerDefaultImpl implements PriceProducer {

    private final ApplicationContext ctx;

    public PriceProducerDefaultImpl(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public Price getPriceDefaultInstance(String departmentId, float value) {
        return (Price) ctx.getBean("priceDefault", departmentId, value);
    }
}
