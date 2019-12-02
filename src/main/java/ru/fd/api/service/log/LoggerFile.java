package ru.fd.api.service.log;

import ru.fd.api.service.time.CurrentDateTime;

public interface LoggerFile {
    void writeLogFile(String type, CurrentDateTime currentDateTime, String message);
}
