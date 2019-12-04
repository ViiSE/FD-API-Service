package ru.fd.api.service.producer.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.entity.Unit;

@Service("unitProducerDefault")
public class UnitProducerDefaultImpl implements UnitProducer {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public Unit getUnitDefaultInstance(String id, String name) {
        return (Unit) ctx.getBean("unitDefault", id, name);
    }
}
