package ru.fd.api.service.log;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.producer.util.FDAPIServiceDirectoryProducer;
import ru.fd.api.service.time.CurrentDateTime;
import test.producer.FDAPIServiceDirectoryProducerTestImpl;
import test.producer.time.CurrentDateTimeProducerTestImpl;

import java.io.IOException;

import static test.message.TestMessage.*;

public class LoggerServiceDefaultTestNG {

    private LoggerService loggerServer;

    @BeforeClass
    public void setUpClass() {
        FDAPIServiceDirectoryProducer dirProducer = new FDAPIServiceDirectoryProducerTestImpl();
        LogDirectory logDirectory = new LogDirectoryTestImpl(dirProducer);
        LoggerFile loggerFile = new LoggerFileDefaultImpl(logDirectory);
        LoggerWindow loggerWindow = new LoggerWindowSlf4jImpl();
        CurrentDateTime curDateTime = new CurrentDateTimeProducerTestImpl().getCurrentDateTimeDefaultInstance();
        loggerServer = new LoggerServiceDefaultImpl(loggerFile, loggerWindow, curDateTime);

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
