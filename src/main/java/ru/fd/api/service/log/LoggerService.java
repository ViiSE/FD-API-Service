package ru.fd.api.service.log;

public interface LoggerService {
    void info(Class<?> clazz, String message);
    void error(Class<?> clazz, String message);
}
