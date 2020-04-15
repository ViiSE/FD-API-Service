/*
 * Copyright 2019 ViiSE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.fd.api.service.log;

import org.springframework.stereotype.Component;
import ru.fd.api.service.time.CurrentDateTime;

import static ru.fd.api.service.log.LogMessageType.ERROR;
import static ru.fd.api.service.log.LogMessageType.INFO;

@Component("loggerService")
public class LoggerServiceImpl implements LoggerService {

    private final LoggerFile loggerFile;
    private final LoggerWindow loggerWindow;
    private final CurrentDateTime currentDateTime;

    public LoggerServiceImpl(LoggerFile loggerFile, LoggerWindow loggerWindow, CurrentDateTime currentDateTime) {
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
