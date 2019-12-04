package ru.fd.api.service.producer.util;

import ru.fd.api.service.util.FDAPIServiceDirectory;

public interface FDAPIServiceDirectoryProducer {
    FDAPIServiceDirectory getFdAPIServiceCurrentDirectoryInstance();
}
