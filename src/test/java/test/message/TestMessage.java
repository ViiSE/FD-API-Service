/* 
 * Copyright 2019 ViiSE.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package test.message;

import org.testng.ITestResult;
import ru.fd.api.service.time.CurrentDateTimeImpl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ViiSE
 */
public class TestMessage {
    
    private enum IDENTIFY {
        BEGIN {
            @Override
            public String toString() { return "BEGIN"; }
        },
        END {
            @Override
            public String toString() { return "END"; }
        }
    }

    private static void printTestMessage(String className, String methodName, IDENTIFY identify) {
        System.out.println("-----------------------------------------------");
        System.out.println(className +  ". Method:" + methodName + ". Test " + identify.toString());
        System.out.println("-----------------------------------------------");
    }

    private static void printTestMessage(String className, IDENTIFY identify) {
        System.out.println("-----------------------------------------------");
        System.out.println(className + ". Test " + identify.toString());
        System.out.println("-----------------------------------------------");
    }

    private static void printTestMessage(String methodName) {
        System.out.println("[Method: " + methodName + "]");
        System.out.println("-----------------------------------------------");
    }

    public static void testBegin(String className, String methodName) {
        printTestMessage(className, methodName, IDENTIFY.BEGIN);
    }

    public static void testBegin(Class<?> clazz, String methodName) {
        printTestMessage(clazz.getSimpleName(), methodName, IDENTIFY.BEGIN);
    }

    public static void testBegin(String className) {
        printTestMessage(className, IDENTIFY.BEGIN);
    }

    public static void testBegin(Class<?> clazz) {
        printTestMessage(clazz.getSimpleName(), IDENTIFY.BEGIN);
    }

    public static void testEnd(String className, String methodName) {
        printTestMessage(className, methodName, IDENTIFY.END);
    }

    public static void testEnd(Class<?> clazz, String methodName) {
        printTestMessage(clazz.getSimpleName(), methodName, IDENTIFY.END);
    }

    public static void testEnd(String className) {
        printTestMessage(className, IDENTIFY.END);
    }

    public static void testEnd(Class<?> clazz) {
        printTestMessage(clazz.getSimpleName(), IDENTIFY.END);
    }

    public static void testMethod(String methodName) {
        printTestMessage(methodName);
    }

    public static void catchMessage(Exception ex) {
        System.out.println("CATCH! Exception: " + ex.getMessage());
    }

    public static void printTestTime(ITestResult tr) {
        long milliseconds = tr.getEndMillis() - tr.getStartMillis();
        System.out.println(new SimpleDateFormat("'Elapsed time: 'mm'm' ss's' SSS'ms'").format(new Date(milliseconds)));
    }

    public static void beginWriteTestTime() {
        writeToFile(new CurrentDateTimeImpl().dateTimeWithDot() + " - TEST BEGIN");
    }

    public static void endWriteTestTime() {
        writeToFile(new CurrentDateTimeImpl().dateTimeWithDot() + " - TEST END");
    }

    public static void writeTestTime(String className) {
        writeToFile(className);
    }

    public static void writeTestTime(ITestResult tr) {
        long milliseconds = tr.getEndMillis() - tr.getStartMillis();
        String time = new SimpleDateFormat("mm'm' ss's' SSS'ms'").format(new Date(milliseconds));
        writeToFile("\t" + tr.getMethod().getMethodName() + "() - " + time);
    }

    private static void writeToFile(String message) {
        // FIXME: 24.12.2019 ADD FILENAME IN XML SUITE
        File file = new File(System.getProperty("user.dir") + File.separator + "elapse_time_test_14_01_2020");

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(message);
            bw.newLine();
        } catch (IOException ex) {
            System.out.println("FILE elapse_time_test_14_01_2020 IS NOT FOUND!");
        }
    }
}
