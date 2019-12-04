package test.producer;

import ru.fd.api.service.producer.util.FDAPIServiceDirectoryProducer;
import ru.fd.api.service.util.FDAPIServiceCurrentDirectoryImpl;
import ru.fd.api.service.util.FDAPIServiceDirectory;

public class FDAPIServiceDirectoryProducerTestImpl implements FDAPIServiceDirectoryProducer {

    @Override
    public FDAPIServiceDirectory getFdAPIServiceCurrentDirectoryInstance() {
        return new FDAPIServiceCurrentDirectoryImpl();
    }
}
