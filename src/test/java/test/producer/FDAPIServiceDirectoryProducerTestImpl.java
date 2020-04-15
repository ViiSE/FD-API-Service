package test.producer;

import ru.fd.api.service.producer.os.FDAPIServiceDirectoryProducer;
import ru.fd.api.service.os.FDAPIServiceCurrentDirectoryImpl;
import ru.fd.api.service.os.FDAPIServiceDirectory;

public class FDAPIServiceDirectoryProducerTestImpl implements FDAPIServiceDirectoryProducer {

    @Override
    public FDAPIServiceDirectory getFdAPIServiceCurrentDirectoryInstance() {
        return new FDAPIServiceCurrentDirectoryImpl();
    }
}
