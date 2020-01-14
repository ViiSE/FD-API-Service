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

import ru.fd.api.service.entity.OrderProductSimpleImpl;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.entity.OrderProductsDefaultImpl;

import java.util.ArrayList;

public class OrderProductsCreatorTest {

    public Products create() {
        return new OrderProductsDefaultImpl(new ArrayList<>() {{
            add(new OrderProductSimpleImpl("id1", 5));
            add(new OrderProductSimpleImpl("id2", 10));
        }});
    }
}
