package ru.fd.api.service.log;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.time.CurrentDateTime;

import static ru.fd.api.service.log.LogMessageType.ERROR;
import static ru.fd.api.service.log.LogMessageType.INFO;

@Component("loggerServiceDefault")
@Scope("prototype")
public class LoggerServiceDefaultImpl implements LoggerService {

    private final LoggerFile loggerFile;
    private final LoggerWindow loggerWindow;
    private final CurrentDateTime currentDateTime;

    public LoggerServiceDefaultImpl(LoggerFile loggerFile, LoggerWindow loggerWindow, CurrentDateTime currentDateTime) {
        this.loggerFile = loggerFile;
        this.loggerWindow = loggerWindow;
        this.currentDateTime = currentDateTime;
    }

    @Override
    public void info(Class<?> clazz, String message) {
        log(INFO, clazz, message);
    }

    @Override
    public void error(Class<?> clazz, String message) {
        log(ERROR, clazz, message);
    }

    private String formatString(String className, String message) {
        return String.format("{%s} : %s", className, message);
    }

    private void log(LogMessageType type, Class<?> clazz, String message) {
        loggerFile.writeLogFile(type.stringValue(), currentDateTime, formatString(clazz.getSimpleName(), message));
        loggerWindow.printLog(type.stringValue(), currentDateTime, formatString(clazz.getSimpleName(), message));
    }
}
