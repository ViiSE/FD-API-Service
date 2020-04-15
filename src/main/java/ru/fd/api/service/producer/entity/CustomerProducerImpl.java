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

package ru.fd.api.service.producer.entity;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.entity.Customer;

@Service("customerProducer")
public class CustomerProducerImpl implements CustomerProducer {

    private final ApplicationContext ctx;

    public CustomerProducerImpl(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public Customer getCustomerSimpleInstance(short type) {
        return (Customer) ctx.getBean("customerSimple", type);
    }

    @Override
    public Customer getCustomerWithNameInstance(Customer customer, String name) {
        return (Customer) ctx.getBean("customerWithName", customer, name);
    }

    @Override
    public Customer getCustomerWithEmailInstance(Customer customer, String email) {
        return (Customer) ctx.getBean("customerWithEmail", customer, email);
    }

    @Override
    public Customer getCustomerWithPhoneNumberInstance(Customer customer, String phoneNumber) {
        return (Customer) ctx.getBean("customerWithPhoneNumber", customer, phoneNumber);
    }

    @Override
    public Customer getCustomerFromCompanyInstance(Customer customer, String inn, String kpp) {
        return (Customer) ctx.getBean("customerFromCompany", customer, inn, kpp);
    }

    @Override
    public Customer getCustomerWithNotifiedInstance(Customer customer, short notified, long orderId) {
        return (Customer) ctx.getBean("customerWithNotified", customer, notified, orderId);
    }
}
