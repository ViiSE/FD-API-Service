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

package ru.fd.api.service.util;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

public class FDAPIServiceCurrentDirectoryTestNG {

    private FDAPIServiceDirectory fdapiServiceDirectory;

    @BeforeClass
    public void setUpClass() {
        fdapiServiceDirectory = new FDAPIServiceCurrentDirectoryImpl();
    }

    @Test
    public void directory() {
        testBegin("FDAPIServiceCurrentDirectory", "directory()");

        String dir = fdapiServiceDirectory.directory();
        assertTrue(dir.contains("FD-API-Service"), "The directory is not current!");
        System.out.println(dir);

        testEnd("FDAPIServiceCurrentDirectory", "directory()");
    }
}