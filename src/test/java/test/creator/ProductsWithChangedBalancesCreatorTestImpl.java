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

import ru.fd.api.service.creator.ProductsCreator;
import ru.fd.api.service.entity.*;
import test.producer.entity.ProductProducerTestImpl;

import java.util.ArrayList;
import java.util.List;

public class ProductsWithChangedBalancesCreatorTestImpl implements ProductsCreator {

    @Override
    public Products create() {
        List<Product> products = new ArrayList<>();

        for(int i = 1; i <= 10; i++)
            products.add(createProduct(i));

        return new ProductsDefaultImpl(new ProductProducerTestImpl(), products);
    }

    private Product createProduct(int id) {
        Balances balances = new BalancesDefaultImpl(new ArrayList<>() {{
            add(new BalanceDefaultImpl("dep1", 10));
            add(new BalanceDefaultImpl("dep2", 20));
        }});

        return new ProductWithChangedBalancesImpl("id1", balances);
    }
}
