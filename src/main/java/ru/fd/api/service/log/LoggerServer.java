package ru.fd.api.service.log;

public interface LoggerServer {
    void info(Class<?> clazz, String message);
    void error(Class<?> clazz, String message);
}
