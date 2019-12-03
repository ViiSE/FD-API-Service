package ru.fd.api.service.producer.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.entity.Status;
import ru.fd.api.service.entity.Statuses;
import ru.fd.api.service.entity.Unit;
import ru.fd.api.service.entity.Units;

import java.util.List;

@Service("unitsProducerDefault")
public class UnitsProducerDefaultImpl implements UnitsProducer {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public Units getUnitsDefaultInstance(List<Unit> unit) {
        return (Units) ctx.getBean("unitsDefault", unit);
    }
}
