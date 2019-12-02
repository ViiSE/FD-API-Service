package ru.fd.api.service.producer.time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.time.CurrentDateTime;

@Service("currentDateTimeProducerDefault")
public class CurrentDateTimeProducerDefaultImpl implements CurrentDateTimeProducer {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public CurrentDateTime getCurrentDateTimeDefaultInstance() {
        return ctx.getBean("currentDateTimeDefault", CurrentDateTime.class);
    }
}
