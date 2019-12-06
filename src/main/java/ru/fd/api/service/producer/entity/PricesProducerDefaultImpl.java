package ru.fd.api.service.producer.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.entity.Price;
import ru.fd.api.service.entity.Prices;

import java.util.List;

@Service("pricesProducerDefault")
public class PricesProducerDefaultImpl implements PricesProducer {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public Prices getPricesDefaultInstance(List<Price> prices) {
        return (Prices) ctx.getBean("pricesDefault", prices);
    }
}
