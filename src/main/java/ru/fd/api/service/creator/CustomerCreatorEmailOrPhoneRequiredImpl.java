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

package ru.fd.api.service.creator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.fd.api.service.data.CustomerPojo;
import ru.fd.api.service.entity.Customer;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.producer.entity.CustomerProducer;

@Service("customerCreatorEmailOrPhoneRequired")
@Scope("prototype")
public class CustomerCreatorEmailOrPhoneRequiredImpl implements CustomerCreator {

    private final CustomerPojo customerPojo;
    private final CustomerProducer customerProducer;

    public CustomerCreatorEmailOrPhoneRequiredImpl(CustomerPojo customerPojo, CustomerProducer customerProducer) {
        this.customerPojo = customerPojo;
        this.customerProducer = customerProducer;
    }

    @Override
    public Customer create() throws CreatorException {
        // TODO: 14.01.2020 ADDED EMPTY IMPL INSTEAD NULL
        checkCustomer();

        checkPhoneAndEmail();

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

    private void checkPhoneAndEmail() throws CreatorException {
        if(!checkPhone())
            if(!checkEmail())
                throw new CreatorException("Phone number or email required");
    }

    private boolean checkPhone() {
        return customerPojo.getPhoneNumber() != null && !customerPojo.getPhoneNumber().isEmpty();
    }

    private boolean checkEmail() {
        return customerPojo.getEmail() != null && !customerPojo.getEmail().isEmpty();
    }

    private void checkCustomer() throws CreatorException {
        // TODO: 16.01.2020 NEW INTERFACE FOR CHECKER
        if(customerPojo == null)
            throw new CreatorException("Customer required");

        if(customerPojo.getType() != 0 && customerPojo.getType() != 1)
            throw new CreatorException("Customer: Type required");

        if(customerPojo.getType() == 1) {
            if(customerPojo.getInn().isEmpty())
                throw new CreatorException("Customer: Inn required");

            if(customerPojo.getKpp().isEmpty())
                throw new CreatorException("Customer: Kpp required");
        }
    }
}
