package ru.fd.api.service.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.fd.api.service.time.CurrentDateTime;

import static ru.fd.api.service.log.LogMessageType.ERROR;
import static ru.fd.api.service.log.LogMessageType.INFO;

@Component("loggerWindowSlf4j")
public class LoggerWindowSlf4jImpl implements LoggerWindow {

    private static final Logger loggerWindow = LoggerFactory.getLogger(LoggerService.class);

    @Override
    public void printLog(String type, CurrentDateTime currentDateTime, String message) {
        if(type.equals(ERROR.stringValue()))
            loggerWindow.error(message);
        else if(type.equals(INFO.stringValue()))
            loggerWindow.info(message);
    }
}
