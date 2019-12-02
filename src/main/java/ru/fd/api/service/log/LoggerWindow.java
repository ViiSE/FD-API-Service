package ru.fd.api.service.log;

import ru.fd.api.service.time.CurrentDateTime;

public interface LoggerWindow {
    void printLog(String type, CurrentDateTime currentDateTime, String message);
}
