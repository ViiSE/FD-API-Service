package ru.fd.api.service.producer.creator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.creator.AttributeGroupsCreator;
import ru.fd.api.service.creator.BalancesCreator;
import ru.fd.api.service.repository.AttributeGroupsRepository;
import ru.fd.api.service.repository.BalancesRepository;

@Service("balancesCreatorProducerDefault")
public class BalancesCreatorProducerDefaultImpl implements BalancesCreatorProducer {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public BalancesCreator getBalancesCreatorDefaultInstance(BalancesRepository balancesRepository) {
        return (BalancesCreator) ctx.getBean("balancesCreatorDefault", balancesRepository);
    }
}