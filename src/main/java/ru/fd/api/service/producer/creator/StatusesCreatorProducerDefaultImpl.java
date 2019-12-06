package ru.fd.api.service.producer.creator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.creator.StatusesCreator;
import ru.fd.api.service.repository.StatusesRepository;

@Service("statusesCreatorProducerDefault")
public class StatusesCreatorProducerDefaultImpl implements StatusesCreatorProducer {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public StatusesCreator getStatusesCreatorDefaultInstance(StatusesRepository statusesRepository) {
        return (StatusesCreator) ctx.getBean("statusesCreatorDefault", statusesRepository);
    }
}