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

import ru.fd.api.service.creator.DeliveryCreator;
import ru.fd.api.service.entity.*;
import ru.fd.api.service.exception.CreatorException;

import java.time.LocalDate;

public class DeliveryCreatorTestImpl implements DeliveryCreator {

    @Override
    public Delivery create() {
        return new DeliveryWithDateImpl(
                new DeliveryWithTimeIdImpl(
                        new DeliveryWithDepartmentIdImpl(
                                new DeliverySimpleImpl(
                                        (short) 0,
                                        "city_1",
                                        "st. Example, 404"),
                                "dep_1"),
                        (short) 0),
                LocalDate.now());
    }
}
