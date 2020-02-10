/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.creator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.fd.api.service.constant.Processors;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.entity.ProductsFailedImpl;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.repository.processor.ProductsRepositoryProcessors;

@Service("productsWithChangedBalancesCreator")
@Scope("prototype")
public class ProductsWithChangedBalancesCreatorImpl implements ProductsCreator {

    private final ProductsRepositoryProcessors prRepoPrc;
    private final long orderId;

    public ProductsWithChangedBalancesCreatorImpl(ProductsRepositoryProcessors prRepoPrc, long orderId) {
        this.prRepoPrc = prRepoPrc;
        this.orderId = orderId;
    }

    @Override
    public Products create() throws CreatorException {
        Products products;
        if(orderId == -1)
            products = (Products) prRepoPrc.processor(Processors.CHANGED_BALANCES).apply("nothing");
        else
            products = (Products) prRepoPrc.processor(Processors.CHANGED_BALANCES_WITH_ORDER_ID).apply(orderId);

        if(products instanceof ProductsFailedImpl)
            throw new CreatorException(products.formForSend().toString());

        return products;
    }
}
