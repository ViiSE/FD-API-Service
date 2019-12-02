package ru.fd.api.service.producer.log;

import ru.fd.api.service.util.FDAPIServiceDirectory;

public interface FDAPIServiceDirectoryProducer {
    FDAPIServiceDirectory getFdAPIServiceCurrentDirectoryInstance();
}
