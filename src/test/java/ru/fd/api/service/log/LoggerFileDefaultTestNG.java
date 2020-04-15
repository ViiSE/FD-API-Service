package ru.fd.api.service.log;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.producer.os.FDAPIServiceDirectoryProducer;
import ru.fd.api.service.time.CurrentDateTime;
import ru.fd.api.service.time.CurrentDateTimeImpl;
import test.producer.FDAPIServiceDirectoryProducerTestImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.testng.Assert.*;
import static test.message.TestMessage.*;

public class LoggerFileDefaultTestNG {

    private LogDirectory logDirectory;
    private LoggerFile loggerFile;
    private CurrentDateTime curDateTime;

    @BeforeClass
    public void setUpClass() {
        FDAPIServiceDirectoryProducer dirProducer = new FDAPIServiceDirectoryProducerTestImpl();
        logDirectory = new LogDirectoryTestImpl(dirProducer);
        loggerFile = new LoggerFileImpl(logDirectory);
        curDateTime = new CurrentDateTimeImpl();

        testBegin("LoggerFileDefault");
    }

    @Test(priority = 1)
    public void writeLogFile_info() throws IOException {
        testMethod("writeLogFile() [info]");
        test(LogMessageType.INFO.stringValue());
    }

    @Test(priority = 2)
    public void writeLogFile_error() throws IOException {
        testMethod("writeLogFile() [error]");
        test(LogMessageType.ERROR.stringValue());
    }

    @Test(priority = 3)
    public void writeLogFile_after_write_info_and_error() throws IOException {
        testMethod("writeLogFile() [after write info and error]");

        File logFile = createAndCheckFile();
        BufferedReader br = new BufferedReader(new FileReader(logFile));

        StringBuilder foundText = new StringBuilder();
        String st;
        while((st = br.readLine()) != null)
                foundText.append(st).append("\n");
        System.out.println(foundText.toString());
        assertTrue(foundText.toString().contains(LogMessageType.INFO.stringValue() + ": Test write log") &&
                foundText.toString().contains(LogMessageType.ERROR.stringValue() + ": Test write log"),
                "File not contains records!");
        System.out.println("Text in log file " + logFile.getName() + ": ");
        System.out.println(foundText);
    }

    @Test(priority = 4)
    public void writeLogFile_directory_not_found() {
        testMethod("writeLogFile() [directory not found]");

        LogDirectory logDirectory = new LogDirectoryExceptionTestImpl();
        LoggerFile exLoggerFile = new LoggerFileImpl(logDirectory);
        exLoggerFile.writeLogFile(LogMessageType.INFO.stringValue(), curDateTime, "Exception!");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }

    @AfterClass
    public void teardownClass() {
        String fullFileName = logDirectory.directory() + "/log_" + curDateTime.dateLog() + ".txt";
        File logFile = new File(fullFileName);
        if(logFile.delete())
            System.out.println("File " + logFile.getName() + " is delete");
        testEnd("LoggerFileDefault", "writeLogFile()");
    }

    private void test(String messageType) throws IOException {
        loggerFile.writeLogFile(messageType, curDateTime, "Test write log");
        File logFile = createAndCheckFile();
        BufferedReader br = new BufferedReader(new FileReader(logFile));
        String st;
        while((st = br.readLine()) != null) {
            if(st.contains("[" + curDateTime.dateWithDot()))
                if(st.contains(LogMessageType.INFO.stringValue() + ": Test write log"))
                    break;
        }
    }

    private File createAndCheckFile() {
        String fullFileName = logDirectory.directory() + "/log_" + curDateTime.dateLog() + ".txt";
        File logFile = new File(fullFileName);
        assertNotNull(logFile, "Log file is null!");
        assertNotEquals(logFile.length(), 0, "Log File is empty!");

        return logFile;
    }
}
