package ru.fd.api.service.process;

import ru.fd.api.service.exception.ProcessException;

public interface ProcessesChain<R, P> {
    R answer(P param) throws ProcessException;
}
