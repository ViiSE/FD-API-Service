/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package test.repository.processor;

import ru.fd.api.service.entity.*;
import ru.fd.api.service.repository.processor.ProductsRepositoryProcessor;

import java.util.ArrayList;

public class ChangedBalancesRepositoryWithOrderIdProcessorTestImpl implements ProductsRepositoryProcessor {

    @Override
    public Products apply(Object orderIdObj) {
        Balances b1 =new BalancesDefaultImpl(
                new ArrayList<>() {{
                    add(new BalanceDefaultImpl("dep1", 25));
                    add(new BalanceDefaultImpl("dep2", 20)); }});

        Balances b2 = new BalancesDefaultImpl(
                        new ArrayList<>() {{
                            add(new BalanceDefaultImpl("dep1", 25));
                            add(new BalanceDefaultImpl("dep2", 20)); }});

        return new ProductsDefaultImpl(null, new ArrayList<>() {{
            add(new ProductWithChangedBalancesImpl("id1", b1));
            add(new ProductWithChangedBalancesImpl("id2", b2));
        }});
    }
}
