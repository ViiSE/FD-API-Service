package test.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import ru.fd.api.service.producer.log.FDAPIServiceDirectoryProducer;
import ru.fd.api.service.util.FDAPIServiceCurrentDirectoryImpl;
import ru.fd.api.service.util.FDAPIServiceDirectory;

public class FDAPIServiceDirectoryProducerTestImpl implements FDAPIServiceDirectoryProducer {

    @Override
    public FDAPIServiceDirectory getFdAPIServiceCurrentDirectoryInstance() {
        return new FDAPIServiceCurrentDirectoryImpl();
    }
}
