package ru.fd.api.service.log;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.os.FDAPIServiceCurrentDirectoryImpl;
import ru.fd.api.service.time.CurrentDateTime;
import ru.fd.api.service.time.CurrentDateTimeImpl;

import static test.message.TestMessage.*;

public class LoggerServiceDefaultTestNG {

    private LoggerService loggerServer;

    @BeforeClass
    public void setUpClass() {
        LogDirectory logDirectory = new LogDirectoryTestImpl(new FDAPIServiceCurrentDirectoryImpl());
        LoggerFile loggerFile = new LoggerFileImpl(logDirectory);
        LoggerWindow loggerWindow = new LoggerWindowSlf4jImpl();
        CurrentDateTime curDateTime = new CurrentDateTimeImpl();
        loggerServer = new LoggerServiceImpl(loggerFile, loggerWindow, curDateTime);

        testBegin("LoggerServiceDefault");
    }

    @Test(priority = 1)
    public void info() {
        testMethod("info()");

        loggerServer.info(LoggerServiceDefaultTestNG.class, "Test logger service");
    }

    @Test(priority = 2)
    public void error() {
        testMethod("error()");

        loggerServer.error(LoggerServiceDefaultTestNG.class, "Test logger service");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }

    @AfterClass
    public void teardownClass() {
        testEnd("LoggerServiceDefault");
    }
}
