package ru.fd.api.service.producer.entity;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.entity.Unit;
import ru.fd.api.service.entity.Units;

import java.util.List;

@Service("unitsProducerDefault")
public class UnitsProducerDefaultImpl implements UnitsProducer {

    private ApplicationContext ctx;

    public UnitsProducerDefaultImpl(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public Units getUnitsDefaultInstance(List<Unit> unit) {
        return (Units) ctx.getBean("unitsDefault", unit);
    }
}
