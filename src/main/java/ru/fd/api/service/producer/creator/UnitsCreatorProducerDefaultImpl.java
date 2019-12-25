package ru.fd.api.service.producer.creator;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.creator.UnitsCreator;
import ru.fd.api.service.repository.UnitsRepository;

@Service("unitsCreatorProducerDefault")
public class UnitsCreatorProducerDefaultImpl implements UnitsCreatorProducer {

    private final ApplicationContext ctx;

    public UnitsCreatorProducerDefaultImpl(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public UnitsCreator getUnitsCreatorDefaultInstance(UnitsRepository unitsRepository) {
        return (UnitsCreator) ctx.getBean("unitsCreatorDefault", unitsRepository);
    }
}