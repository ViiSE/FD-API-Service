package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.BalancesPojo;

import java.util.ArrayList;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

public class BalancesDefaultTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Balances balances;

    @BeforeClass
    @Parameters({"departmentId1", "quantity1", "departmentId2", "quantity2"})
    public void setUpClass(String departmentId1, int quantity1, String departmentId2, int quantity2) {
        assertNotNull(departmentId1, "Department ID cannot be null!");
        assertFalse(departmentId1.isEmpty(), "Department ID is empty!");
        assertFalse(quantity1 < 0, "Quantity is less than 0!");

        assertNotNull(departmentId2, "Department ID cannot be null!");
        assertFalse(departmentId2.isEmpty(), "Department ID is empty!");
        assertFalse(quantity2 < 0, "Quantity is less than 0!");

        Balance balance1 = new BalanceDefaultImpl(departmentId1, quantity1);
        Balance balance2 = new BalanceDefaultImpl(departmentId2, quantity2);

        balances = new BalancesDefaultImpl(new ArrayList<>() {{ add(balance1); add(balance2); }});
        assertNotNull(balances, "Balances is null!");
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("BalancesDefault", "formForSend()");

        BalancesPojo balancesPojo = (BalancesPojo) balances.formForSend();
        assertNotNull(balancesPojo, "BalancesPojo is null!");
        System.out.println(mapper.writeValueAsString(balancesPojo));

        testEnd("BalancesDefault", "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
