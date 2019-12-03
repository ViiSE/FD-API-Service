package ru.fd.api.service.producer.creator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.creator.StatusesCreator;
import ru.fd.api.service.creator.UnitsCreator;
import ru.fd.api.service.repository.StatusesRepository;
import ru.fd.api.service.repository.UnitsRepository;

@Service("unitsCreatorProducerDefault")
public class UnitsCreatorProducerDefaultImpl implements UnitsCreatorProducer {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public UnitsCreator getUnitsCreatorDefaultInstance(UnitsRepository unitsRepository) {
        return (UnitsCreator) ctx.getBean("statusesCreatorDefault", unitsRepository);
    }
}