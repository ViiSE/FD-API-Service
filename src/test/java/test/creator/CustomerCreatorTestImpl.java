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

import ru.fd.api.service.creator.CustomerCreator;
import ru.fd.api.service.entity.*;
import ru.fd.api.service.exception.CreatorException;

public class CustomerCreatorTestImpl implements CustomerCreator {

    @Override
    public Customer create() {
        return new CustomerFromCompanyImpl(
                new CustomerWithPhoneNumberImpl(
                        new CustomerWithEmailImpl(
                                new CustomerWithNameImpl(
                                        new CustomerSimpleImpl((short) 0),
                                        "John Doe"),
                                "example@example.com"),
                        "89091111111"),
                "2323313",
                "4676546");
    }
}
