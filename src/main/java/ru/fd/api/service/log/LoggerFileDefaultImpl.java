package ru.fd.api.service.log;

import org.springframework.stereotype.Component;
import ru.fd.api.service.time.CurrentDateTime;

import java.io.*;

@Component("loggerFileDefaultImpl")
public class LoggerFileDefaultImpl implements LoggerFile {

    private final LogDirectory logDirectory;

    public LoggerFileDefaultImpl(LogDirectory logDirectory) {
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
