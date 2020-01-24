/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.producer.repository.processor;

import ru.fd.api.service.repository.processor.ProductsRepositoryProcessor;

public interface ProductsRepositoryProcessorProducer {
    ProductsRepositoryProcessor getChangedBalancesRepositoryWithOrderIdProcessorInstance();
    ProductsRepositoryProcessor getChangedValancesRepositoryProcessorInstance();
}
