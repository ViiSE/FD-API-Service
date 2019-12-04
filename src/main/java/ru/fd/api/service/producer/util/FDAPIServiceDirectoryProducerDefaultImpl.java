package ru.fd.api.service.producer.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.util.FDAPIServiceDirectory;

@Service("FDAPIServiceDirectoryProducerDefault")
public class FDAPIServiceDirectoryProducerDefaultImpl implements FDAPIServiceDirectoryProducer {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public FDAPIServiceDirectory getFdAPIServiceCurrentDirectoryInstance() {
        return ctx.getBean("FDAPIServiceCurrentDirectory", FDAPIServiceDirectory.class);
    }
}
