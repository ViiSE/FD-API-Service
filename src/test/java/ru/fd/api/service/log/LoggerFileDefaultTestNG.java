package ru.fd.api.service.log;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.producer.util.FDAPIServiceDirectoryProducer;
import ru.fd.api.service.time.CurrentDateTime;
import test.producer.FDAPIServiceDirectoryProducerTestImpl;
import test.producer.time.CurrentDateTimeProducerTestImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.testng.Assert.*;
import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

public class LoggerFileDefaultTestNG {

    private LogDirectory logDirectory;
    private LoggerFile loggerFile;
    private CurrentDateTime curDateTime;

    @BeforeClass
    public void setUpClass() {
        FDAPIServiceDirectoryProducer dirProducer = new FDAPIServiceDirectoryProducerTestImpl();
        logDirectory = new LogDirectoryTestImpl(dirProducer);
        loggerFile = new LoggerFileDefaultImpl(logDirectory);
        curDateTime = new CurrentDateTimeProducerTestImpl().getCurrentDateTimeDefaultInstance();

        testBegin("LoggerFileDefault", "writeLogFile()");
    }

    @Test(priority = 1)
    public void writeLogFile_info() throws IOException {
        loggerFile.writeLogFile(LogMessageType.INFO.stringValue(), curDateTime, "Test write log");
        String fullFileName = logDirectory.directory() + "/log_" + curDateTime.dateLog() + ".txt";
        File logFile = new File(fullFileName);
        assertNotNull(logFile, "Log file is null!");
        assertNotEquals(logFile.length(), 0, "Log File is empty!");
        BufferedReader br = new BufferedReader(new FileReader(logFile));
        boolean isTextFound = false;
        String foundText = "";
        String st;
        while((st = br.readLine()) != null) {
            if(st.contains("[" + curDateTime.dateWithDot()))
                if(st.contains(LogMessageType.INFO.stringValue() + ": Test write log")) {
                    isTextFound = true;
                    foundText = st;
                    break;
                }
        }

        if(isTextFound)
            System.out.println("Text in log file " + logFile.getName() + ": " + foundText);
    }

    @Test(priority = 2)
    public void writeLogFile_error() throws IOException {
        loggerFile.writeLogFile(LogMessageType.ERROR.stringValue(), curDateTime, "Test write log");
        String fullFileName = logDirectory.directory() + "/log_" + curDateTime.dateLog() + ".txt";
        File logFile = new File(fullFileName);
        assertNotNull(logFile, "Log file is null!");
        assertNotEquals(logFile.length(), 0, "Log File is empty!");
        BufferedReader br = new BufferedReader(new FileReader(logFile));
        boolean isTextFound = false;
        String foundText = "";
        String st;
        while((st = br.readLine()) != null) {
            if(st.contains("[" + curDateTime.dateWithDot()))
                if(st.contains(LogMessageType.ERROR.stringValue() + ": Test write log")) {
                    isTextFound = true;
                    foundText = st;
                    break;
                }
        }

        if(isTextFound)
            System.out.println("Text in log file " + logFile.getName() + ": " + foundText);
    }

    @Test(priority = 3)
    public void writeLogFile_after_write_info_and_error() throws IOException {
        File logFile = createAndCheckFile();
        BufferedReader br = new BufferedReader(new FileReader(logFile));

        StringBuilder foundText = new StringBuilder();
        String st;
        while((st = br.readLine()) != null)
                foundText.append(st).append("\n");
        assertTrue((foundText.toString().contains(LogMessageType.ERROR.stringValue() + ": Test write log") &&
                            foundText.toString().contains(LogMessageType.INFO.stringValue() + ": Test write log")),
                "File not contains records!");
        System.out.println("Text in log file " + logFile.getName() + ": ");
        System.out.println(foundText);
    }

    @Test(priority = 4)
    public void writeLogFile_directory_not_found() {
        LogDirectory logDirectory = new LogDirectoryExceptionTestImpl();
        LoggerFile exLoggerFile = new LoggerFileDefaultImpl(logDirectory);
        exLoggerFile.writeLogFile(LogMessageType.INFO.stringValue(), curDateTime, "Exception!");
    }

    @AfterClass
    public void teardownClass() throws IOException {
        String fullFileName = logDirectory.directory() + "/log_" + curDateTime.dateLog() + ".txt";
        File logFile = new File(fullFileName);
        if(logFile.delete())
            System.out.println("File " + logFile.getName() + " is delete");
        testEnd("LoggerFileDefault", "writeLogFile()");
    }

    private File createAndCheckFile() {
        String fullFileName = logDirectory.directory() + "/log_" + curDateTime.dateLog() + ".txt";
        File logFile = new File(fullFileName);
        assertNotNull(logFile, "Log file is null!");
        assertNotEquals(logFile.length(), 0, "Log File is empty!");

        return logFile;
    }
}
