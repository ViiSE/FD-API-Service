package ru.fd.api.service.producer.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.entity.Status;
import ru.fd.api.service.entity.Statuses;

import java.util.List;

@Service("statusesProducerDefault")
public class StatusesProducerDefaultImpl implements StatusesProducer {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public Statuses getStatusesDefaultInstance(List<Status> statuses) {
        return (Statuses) ctx.getBean("statusesDefault", statuses);
    }
}
