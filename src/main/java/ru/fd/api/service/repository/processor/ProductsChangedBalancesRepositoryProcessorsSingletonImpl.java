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
import ru.fd.api.service.producer.repository.processor.ProductsRepositoryProcessorProducer;

import java.util.HashMap;
import java.util.Map;

@Service("productsChangedBalancesRepositoryProcessorsSingleton")
public class ProductsChangedBalancesRepositoryProcessorsSingletonImpl implements ProductsRepositoryProcessors  {

    private static final Map<String, ProductsRepositoryProcessor> processors = new HashMap<>();

    public ProductsChangedBalancesRepositoryProcessorsSingletonImpl(ProductsRepositoryProcessorProducer productRepoPrcProducer) {
        if(processors.isEmpty()) {
            processors.put(
                    Processors.CHANGED_BALANCES_WITH_ORDER_ID,
                    productRepoPrcProducer.getChangedBalancesRepositoryWithOrderIdProcessorInstance());
            processors.put(
                    Processors.CHANGED_BALANCES,
                    productRepoPrcProducer.getChangedValancesRepositoryProcessorInstance());
        }
    }

    @Override
    public ProductsRepositoryProcessor processor(String key) {
        return processors.get(key);
    }
}
