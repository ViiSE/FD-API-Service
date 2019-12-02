package ru.fd.api.service.log;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.producer.log.FDAPIServiceDirectoryProducer;
import test.producer.FDAPIServiceDirectoryProducerTestImpl;

import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

public class LogDirectoryDefaultTestNG {

    private LogDirectory logDirectory;

    @BeforeClass
    public void setUpClass() {
        FDAPIServiceDirectoryProducer dirProducer = new FDAPIServiceDirectoryProducerTestImpl();
        logDirectory = new LogDirectoryDefaultImpl(dirProducer);

    }

    @Test
    public void directory() {
        testBegin("LogDirectoryDefault", "directory()");

        String logDir = logDirectory.directory();
        assertNotNull(logDir, "Log directory is null!");
        System.out.println("Log directory: " + logDir);

        testEnd("LogDirectoryDefault", "directory()");
    }
}
