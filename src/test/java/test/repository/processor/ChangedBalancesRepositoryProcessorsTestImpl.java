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

import ru.fd.api.service.constant.Processors;
import ru.fd.api.service.repository.processor.ProductsRepositoryProcessor;
import ru.fd.api.service.repository.processor.ProductsRepositoryProcessors;

import java.util.HashMap;
import java.util.Map;

public class ChangedBalancesRepositoryProcessorsTestImpl implements ProductsRepositoryProcessors {

    private final Map<String, ProductsRepositoryProcessor> processors = new HashMap<>();

    public ChangedBalancesRepositoryProcessorsTestImpl() {
        processors.put(Processors.CHANGED_BALANCES, new ChangedBalancesRepositoryProcessorTestImpl());
        processors.put(Processors.CHANGED_BALANCES_WITH_ORDER_ID, new ChangedBalancesRepositoryWithOrderIdProcessorTestImpl());
    }

    @Override
    public ProductsRepositoryProcessor processor(String key) {
        return processors.get(key);
    }
}
