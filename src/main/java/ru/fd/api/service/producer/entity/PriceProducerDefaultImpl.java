package ru.fd.api.service.producer.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.entity.Price;

@Service("priceProducerDefault")
public class PriceProducerDefaultImpl implements PriceProducer {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public Price getPriceDefaultInstance(String departmentId, float value) {
        return (Price) ctx.getBean("priceDefault", departmentId, value);
    }
}
