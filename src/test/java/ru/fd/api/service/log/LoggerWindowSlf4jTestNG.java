package ru.fd.api.service.log;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.time.CurrentDateTime;
import ru.fd.api.service.time.CurrentDateTimeImpl;

import static test.message.TestMessage.printTestTime;
import static test.message.TestMessage.testBegin;

public class LoggerWindowSlf4jTestNG {

    private CurrentDateTime curDateTime;
    private LoggerWindow loggerWindow;

    @BeforeClass
    public void setUpClass() {
        loggerWindow = new LoggerWindowSlf4jImpl();
        curDateTime = new CurrentDateTimeImpl();
        testBegin("LoggerWindowSlf4j", "printLog()");
    }

    @Test
    public void printLog_info() {
        loggerWindow.printLog(LogMessageType.INFO.stringValue(), curDateTime, "Test log");
    }

    @Test
    public void printLog_error() {
        loggerWindow.printLog(LogMessageType.ERROR.stringValue(), curDateTime, "Test log");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
