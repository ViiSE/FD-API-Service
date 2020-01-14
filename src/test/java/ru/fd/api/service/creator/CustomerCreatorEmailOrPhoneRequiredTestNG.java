/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.creator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.*;
import ru.fd.api.service.data.CustomerPojo;
import ru.fd.api.service.entity.Customer;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.producer.entity.CustomerProducer;
import test.creator.CustomerProducerTestImpl;
import test.util.TestUtils;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

public class CustomerCreatorEmailOrPhoneRequiredTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private CustomerProducer customerProducer;

    @BeforeClass
    public void setUpClass() {
        customerProducer = new CustomerProducerTestImpl();
        testBegin("CustomerCreatorEmailOrPhoneRequired");
    }

    @Test(priority = 1)
    @Parameters({"phoneNumber", "email"})
    public void create_phoneNumber_and_email(String phoneNumber, String email) throws CreatorException, JsonProcessingException {
        testMethod("create() [phone number and email]");
        assertTrue(TestUtils.phoneNumberMatches(phoneNumber), "Phone number is not valid!");
        assertTrue(TestUtils.emailMatches(email), "Email is not valid!");

        Customer customer = createCustomer(phoneNumber, email);

        assertTrue(mapper.writeValueAsString(customer.formForSend()).contains("\"phone_number\""));
        assertTrue(mapper.writeValueAsString(customer.formForSend()).contains("\"email\""));
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(customer.formForSend()));
    }

    @Test(priority = 2)
    @Parameters({"email"})
    public void create_email_only_pn_null(String email) throws CreatorException, JsonProcessingException {
        testMethod("create() [email only (phone number is null)]");

        Customer customer = createCustomer(null, email);

        assertTrue(mapper.writeValueAsString(customer.formForSend()).contains("\"email\""));
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(customer.formForSend()));
    }

    @Test(priority = 3)
    @Parameters({"email"})
    public void create_email_only_pn_empty(String email) throws CreatorException, JsonProcessingException {
        testMethod("create() [email only (phone number is empty)]");

        Customer customer = createCustomer("", email);

        assertTrue(mapper.writeValueAsString(customer.formForSend()).contains("\"email\""));
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(customer.formForSend()));
    }

    @Test(priority = 4)
    @Parameters({"phoneNumber"})
    public void create_phoneNumber_only_email_null(String phoneNumber) throws CreatorException, JsonProcessingException {
        testMethod("create() [phone number only (email is null)]");

        Customer customer = createCustomer(phoneNumber, null);

        assertTrue(mapper.writeValueAsString(customer.formForSend()).contains("\"phone_number\""));
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(customer.formForSend()));
    }

    @Test(priority = 5)
    @Parameters({"phoneNumber"})
    public void create_phoneNumber_only_email_empty(String phoneNumber) throws CreatorException, JsonProcessingException {
        testMethod("create() [phone number only (email is empty)]");

        Customer customer = createCustomer(phoneNumber, "");

        assertTrue(mapper.writeValueAsString(customer.formForSend()).contains("\"phone_number\""));
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(customer.formForSend()));
    }

    @Test(priority = 6, expectedExceptions = CreatorException.class, expectedExceptionsMessageRegExp = "Phone number or email required")
    public void create_email_and_phoneNumber_is_missing_null() throws CreatorException {
        testMethod("create() [email and phone number is missing (both null)]");

        CustomerPojo customerPojo = createCustomerPojo(null, null);
        new CustomerCreatorEmailOrPhoneRequiredImpl(customerPojo, customerProducer).create();
    }

    @Test(priority = 7, expectedExceptions = CreatorException.class, expectedExceptionsMessageRegExp = "Phone number or email required")
    public void create_email_and_phoneNumber_is_missing_empty() throws CreatorException {
        testMethod("create() [email and phone number is missing (both empty)]");

        CustomerPojo customerPojo = createCustomerPojo("", "");
        new CustomerCreatorEmailOrPhoneRequiredImpl(customerPojo, customerProducer).create();
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }

    @AfterClass
    public void teardownClass() {
        testEnd("CustomerCreatorEmailOrPhoneRequired");
    }

    private CustomerPojo createCustomerPojo(String phone, String email) {
        CustomerPojo customerPojo = new CustomerPojo();

        if(phone != null)
            customerPojo.setPhoneNumber(phone);

        if(email != null)
            customerPojo.setEmail(email);

        customerPojo.setName("John Doe");
        customerPojo.setType((short) 1);
        customerPojo.setInn("inn numbers");
        customerPojo.setKpp("kpp numbers");

        return customerPojo;
    }

    private Customer createCustomer(String phone, String email) throws CreatorException {
        CustomerPojo customerPojo = createCustomerPojo(phone, email);
        Customer customer = new CustomerCreatorEmailOrPhoneRequiredImpl(customerPojo, customerProducer).create();
        assertNotNull(customer, "Customer is null!");

        return customer;
    }
}
