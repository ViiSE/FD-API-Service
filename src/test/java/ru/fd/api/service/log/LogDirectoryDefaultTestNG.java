package ru.fd.api.service.log;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.producer.os.FDAPIServiceDirectoryProducer;
import test.producer.FDAPIServiceDirectoryProducerTestImpl;

import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

public class LogDirectoryDefaultTestNG {

    private LogDirectory logDirectory;

    @BeforeClass
    public void setUpClass() {
        FDAPIServiceDirectoryProducer dirProducer = new FDAPIServiceDirectoryProducerTestImpl();
        logDirectory = new LogDirectoryImpl(dirProducer);

    }

    @Test
    public void directory() {
        testBegin("LogDirectoryDefault", "directory()");

        String logDir = logDirectory.directory();
        assertNotNull(logDir, "Log directory is null!");
        System.out.println("Log directory: " + logDir);

        testEnd("LogDirectoryDefault", "directory()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
