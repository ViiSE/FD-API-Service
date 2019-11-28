package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.BalancePojo;

import static org.testng.Assert.*;
import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

public class BalanceDefaultTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Balance balance;

    private String departmentId;
    private int quantity;

    @BeforeClass
    @Parameters({"departmentId", "quantity"})
    public void setUpClass(String departmentId, int quantity) {
        assertNotNull(departmentId, "DepartmentID cannot be null!");
        assertFalse(departmentId.isEmpty(), "Department ID is empty!");
        assertFalse(quantity < 0, "Quantity is less than 0!");

        balance = new BalanceDefaultImpl(departmentId, quantity);
        assertNotNull(balance, "Balance is null!");

        this.departmentId = departmentId;
        this.quantity = quantity;
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("BalanceDefault", "formForSend()");

        BalancePojo balancePojo = (BalancePojo) balance.formForSend();
        assertNotNull(balancePojo, "BalancePojo is null!");
        assertEquals(balancePojo.getDepartmentId(), departmentId);
        assertEquals(balancePojo.getQuantity(), quantity);
        System.out.println(mapper.writeValueAsString(balancePojo));

        testEnd("BalanceDefault", "formForSend()");
    }
}
