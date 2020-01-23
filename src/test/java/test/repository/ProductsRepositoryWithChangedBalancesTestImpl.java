/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package test.repository;

import ru.fd.api.service.creator.ProductsCreator;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.repository.ProductsRepository;
import test.creator.ProductsWithChangedBalancesCreatorTestImpl;

public class ProductsRepositoryWithChangedBalancesTestImpl implements ProductsRepository {

    @Override
    public Products readProducts() {
        ProductsCreator productsCreator = new ProductsWithChangedBalancesCreatorTestImpl();
        try {
            return productsCreator.create();
        } catch (CreatorException ignore) {}
        return null;
    }
}
