/*
 *  Copyright 2019 ViiSE.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package ru.fd.api.service.time;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

public class CurrentDateTimeDefaultTestNG {

    private CurrentDateTime currentDateTime;

    @BeforeClass
    public void setUpClass() {
        currentDateTime = new CurrentDateTimeDefaultImpl();
        testBegin("CurrentDateTimeDefault");
    }

    @Test
    public void dateTimeWithDot() throws DateTimeParseException {
        testMethod("dateTimeWithDot()");

        String dateTimeWD = currentDateTime.dateTimeWithDot();
        assertNotNull(dateTimeWD, "DateTimeWithDot is null!");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(dateTimeWD, formatter);
        String result = ldt.format(formatter);
        assertEquals(dateTimeWD, result, "DateTimeWithDot and Result DateTime is not equals!");

        System.out.println("DateTimeWithDot: " + dateTimeWD);
        System.out.println("Result:          " + result);
    }

    @Test
    public void dateLog() throws DateTimeParseException {
        testMethod("dateLog()");

        String datelog = currentDateTime.dateLog();
        assertNotNull(datelog, "DateLog is null!");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate ldt = LocalDate.parse(datelog, formatter);
        String result = ldt.format(formatter);
        assertEquals(datelog, result, "DateLog and Result DateTime is not equals!");

        System.out.println("DateLog: " + datelog);
        System.out.println("Result:  " + result);
    }

    @Test
    public void dateWithDot() throws DateTimeParseException {
        testMethod("dateWithDot()");

        String dateWithDot = currentDateTime.dateWithDot();
        assertNotNull(dateWithDot, "DateWithDot is null!");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate ldt = LocalDate.parse(dateWithDot, formatter);
        String result = ldt.format(formatter);
        assertEquals(dateWithDot, result, "DateWithDot and Result DateTime is not equals!");

        System.out.println("DateWithDot: " + dateWithDot);
        System.out.println("Result:      " + result);
    }

    @AfterClass
    public void teardownClass() {
        testEnd("CurrentDateTimeDefault");
    }
}
