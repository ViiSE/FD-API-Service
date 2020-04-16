/*
 * Copyright 2019 ViiSE
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

import org.springframework.stereotype.Component;
import ru.fd.api.service.data.CustomerPojo;
import ru.fd.api.service.entity.Customer;
import ru.fd.api.service.exception.CreateOrderBadRequestException;
import ru.fd.api.service.exception.ProcessException;
import ru.fd.api.service.producer.entity.CustomerProducer;

@Component("psCustomerEmailOrPhoneReq")
public class PsCustomerEmailOrPhoneReqImpl implements Process<Customer, CustomerPojo> {

    private final CustomerProducer customerProducer;

    public PsCustomerEmailOrPhoneReqImpl(CustomerProducer customerProducer) {
        this.customerProducer = customerProducer;
    }

    @Override
    public Customer answer(CustomerPojo customerPojo) throws ProcessException {
        // TODO: 14.01.2020 ADDED EMPTY IMPL INSTEAD NULL
        checkCustomer(customerPojo);
        checkPhoneAndEmail(customerPojo);

        return customerProducer.getCustomerFromCompanyInstance(
                customerProducer.getCustomerWithNameInstance(
                        customerProducer.getCustomerWithEmailInstance(
                                customerProducer.getCustomerWithPhoneNumberInstance(
                                        customerProducer.getCustomerSimpleInstance(customerPojo.getType()),
                                        customerPojo.getPhoneNumber()),
                                customerPojo.getEmail()),
                        customerPojo.getName()),
                customerPojo.getInn(),
                customerPojo.getKpp());
    }

    private void checkPhoneAndEmail(CustomerPojo customerPojo) throws ProcessException {
        if(!checkPhone(customerPojo))
            if(!checkEmail(customerPojo)) {
                String message = "Phone number or email required";
                throw new ProcessException(message, new CreateOrderBadRequestException(message));
            }
    }

    private boolean checkPhone(CustomerPojo customerPojo) {
        return customerPojo.getPhoneNumber() != null && !customerPojo.getPhoneNumber().isEmpty();
    }

    private boolean checkEmail(CustomerPojo customerPojo) {
        return customerPojo.getEmail() != null && !customerPojo.getEmail().isEmpty();
    }

    private void checkCustomer(CustomerPojo customerPojo) throws ProcessException {
        // TODO: 16.01.2020 NEW INTERFACE FOR CHECKER
        if(customerPojo == null) {
            String message = "Customer required";
            throw new ProcessException("Customer required", new CreateOrderBadRequestException(message));
        }

        if(customerPojo.getType() != 0 && customerPojo.getType() != 1) {
            String message = "Customer: Type required";
            throw new ProcessException(message ,new CreateOrderBadRequestException(message));
        }

        if(customerPojo.getType() == 1) {
            if(customerPojo.getInn().isEmpty()) {
                String message = "Customer: Inn required";
                throw new ProcessException(message, new CreateOrderBadRequestException(message));
            }

            if(customerPojo.getKpp().isEmpty()) {
                String message = "Customer: Kpp required";
                throw new ProcessException(message, new CreateOrderBadRequestException(message));
            }
        }
    }
}
