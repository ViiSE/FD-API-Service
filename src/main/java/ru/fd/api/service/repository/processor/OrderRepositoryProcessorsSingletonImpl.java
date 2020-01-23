/*
 *  Copyright 2019 ViiSE.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package ru.fd.api.service.repository.processor;

import org.springframework.stereotype.Service;
import ru.fd.api.service.constant.Processors;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.producer.entity.OrderResponseProducer;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.entity.ProductsProducer;
import ru.fd.api.service.producer.repository.OrderRepositoryProducer;

import java.util.HashMap;
import java.util.Map;

@Service("orderRepositoryProcessorsSingleton")
public class OrderRepositoryProcessorsSingletonImpl implements OrderRepositoryProcessors  {

    private static final Map<String, OrderRepositoryProcessor> processors = new HashMap<>();

    public OrderRepositoryProcessorsSingletonImpl(
            OrderRepositoryProducer orderRepoProducer,
            SQLQueryCreator<String, String> sqlQueryCreator,
            ProductProducer productProducer,
            ProductsProducer orderProductsProducer,
            OrderResponseProducer orderResponseProducer) {
        if(processors.isEmpty()) {
            processors.put(Processors.CREATE_ORDER_DEPRECATED, new CreateOrderRepositoryDeprecatedProcessorImpl(
                    orderRepoProducer,
                    sqlQueryCreator,
                    productProducer,
                    orderProductsProducer,
                    orderResponseProducer));
            processors.put(Processors.CREATE_ORDER, new CreateOrderRepositoryProcessorImpl(
                    orderRepoProducer,
                    sqlQueryCreator,
                    orderResponseProducer));
        }
    }


    @Override
    public OrderRepositoryProcessor processor(String key) {
        return processors.get(key);
    }
}
