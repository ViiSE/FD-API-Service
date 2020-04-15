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

import java.io.*;

@Component("loggerFileImpl")
public class LoggerFileImpl implements LoggerFile {

    private final LogDirectory logDirectory;

    public LoggerFileImpl(LogDirectory logDirectory) {
        this.logDirectory = logDirectory;
    }

    @Override
    public synchronized void writeLogFile(String type, CurrentDateTime currentDateTime, String message) {
        try(OutputStream fout = new FileOutputStream(
                logDirectory.directory() + "log_" + currentDateTime.dateLog() + ".txt", true);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fout))) {
            bw.write("[" + currentDateTime.dateTimeWithDot() + "] " + type + ": " + message);
            bw.newLine();
        } catch(IOException ex) {
            System.out.println("[" + currentDateTime.dateTimeWithDot() + "] Log file Error: " + ex.getMessage());
        }
    }
}
