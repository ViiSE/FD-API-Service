package test.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.producer.time.CurrentDateTimeProducer;
import ru.fd.api.service.time.CurrentDateTime;
import ru.fd.api.service.time.CurrentDateTimeDefaultImpl;

public class CurrentDateTimeProducerTestImpl implements CurrentDateTimeProducer {

    @Override
    public CurrentDateTime getCurrentDateTimeDefaultInstance() {
        return new CurrentDateTimeDefaultImpl();
    }
}
