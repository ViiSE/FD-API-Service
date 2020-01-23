/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.repository.processor;

import org.springframework.stereotype.Service;
import ru.fd.api.service.constant.Processors;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.producer.entity.*;
import ru.fd.api.service.producer.repository.OrderRepositoryProducer;
import ru.fd.api.service.producer.repository.ProductsRepositoryProducer;
import ru.fd.api.service.repository.OrderRepository;
import ru.fd.api.service.repository.ProductsRepository;

import java.util.HashMap;
import java.util.Map;

@Service("productsChangedBalancesRepositoryProcessorsSingleton")
public class ProductsChangedBalancesRepositoryProcessorsSingletonImpl implements ProductsRepositoryProcessors  {

    private static final Map<String, ProductsRepositoryProcessor> processors = new HashMap<>();

    public ProductsChangedBalancesRepositoryProcessorsSingletonImpl(
            OrderProducer orderProducer,
            OrderRepositoryProducer orderRepoProducer,
            BalanceProducer balanceProducer,
            BalancesProducer balancesProducer,
            ProductProducer productProducer,
            ProductsProducer productsProducer,
            ProductsRepositoryProducer productsRepositoryProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        if(processors.isEmpty()) {

            OrderRepository<Void, Order> oRepo = orderRepoProducer
                    .getOrderRepositoryChangedBalancesInstance(sqlQueryCreator, orderProducer);

            ProductsRepository pRepo = productsRepositoryProducer
                    .getProductsRepositoryWithChangedBalancesInstance(
                            productProducer,
                            productsProducer,
                            balanceProducer,
                            balancesProducer,
                            sqlQueryCreator);

            processors.put(Processors.CHANGED_BALANCES_WITH_ORDER_ID, new ChangedBalancesRepositoryWithOrderIdProcessorImpl(
                    oRepo,
                    productsRepositoryProducer,
                    productProducer,
                    productsProducer,
                    balanceProducer,
                    balancesProducer,
                    sqlQueryCreator));
            processors.put(Processors.CHANGED_BALANCES, new ChangedBalancesRepositoryProcessorImpl(
                    oRepo,
                    pRepo,
                    productsProducer));
        }

    }

    @Override
    public ProductsRepositoryProcessor processor(String key) {
        return processors.get(key);
    }
}
