/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package test.creator;

import ru.fd.api.service.entity.*;
import ru.fd.api.service.producer.entity.CustomerProducer;

public class CustomerProducerTestImpl implements CustomerProducer {

    @Override
    public Customer getCustomerSimpleInstance(short type) {
        return new CustomerSimpleImpl(type);
    }

    @Override
    public Customer getCustomerWithNameInstance(Customer customer, String name) {
        return new CustomerWithNameImpl(customer, name);
    }

    @Override
    public Customer getCustomerWithEmailInstance(Customer customer, String email) {
        return new CustomerWithEmailImpl(customer, email);
    }

    @Override
    public Customer getCustomerWithPhoneNumberInstance(Customer customer, String phoneNumber) {
        return new CustomerWithPhoneNumberImpl(customer, phoneNumber);
    }

    @Override
    public Customer getCustomerFromCompanyInstance(Customer customer, String inn, String kpp) {
        return new CustomerFromCompanyImpl(customer, inn, kpp);
    }

    @Override
    public Customer getCustomerWithNotifiedInstance(Customer customer, short notified, long orderId) {
        return null;
    }
}
