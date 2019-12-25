package ru.fd.api.service.producer.util;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.util.FDAPIServiceDirectory;

@Service("FDAPIServiceDirectoryProducerDefault")
public class FDAPIServiceDirectoryProducerDefaultImpl implements FDAPIServiceDirectoryProducer {

    private final ApplicationContext ctx;

    public FDAPIServiceDirectoryProducerDefaultImpl(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public FDAPIServiceDirectory getFdAPIServiceCurrentDirectoryInstance() {
        return ctx.getBean("FDAPIServiceCurrentDirectory", FDAPIServiceDirectory.class);
    }
}
