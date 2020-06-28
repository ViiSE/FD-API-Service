/*
 * Copyright 2020 ViiSE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.fd.api.service.process;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;
import ru.fd.api.service.entity.Delivery;
import ru.fd.api.service.entity.Sendable;
import ru.fd.api.service.exception.ProcessException;
import ru.fd.api.service.process.test.PsDeliveryTestImpl;

import static org.testng.Assert.assertNotNull;

public class PsDeliveryTestNG {

    @Test
    public void answer() throws ProcessException, JsonProcessingException {
        Process<Delivery, Void> pr = new PsDeliveryTestImpl();
        Sendable delivery = pr.answer(null);
        assertNotNull(delivery, "Delivery is null!");
        String print = new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(delivery.formForSend());
        System.out.println(print);
    }
}
