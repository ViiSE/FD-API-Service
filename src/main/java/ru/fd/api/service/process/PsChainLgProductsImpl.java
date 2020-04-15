/*
 * Copyright 2019 ViiSE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.fd.api.service.process;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.ProcessException;
import ru.fd.api.service.log.LoggerService;

import java.util.List;

@Component("psChainLgProducts")
public class PsChainLgProductsImpl implements Process<Products, List<String>> {

    private final LoggerService logger;
    private final Process<Products, List<String>> chain;

    public PsChainLgProductsImpl(
            LoggerService logger,
            @Qualifier("psChainProducts") Process<Products, List<String>> chain) {
        this.logger = logger;
        this.chain = chain;
    }

    @Override
    public Products answer(List<String> with) throws ProcessException {
        try {
            Products products = chain.answer(with);
            logger.info(PsChainLgProductsImpl.class, "Site request products with " + with.toString());
            return products;
        } catch (ProcessException ex) {
            logger.error(PsChainLgProductsImpl.class, ex.getMessage() + " <CAUSE>: " + ex.getCause());
            throw ex;
        }
    }
}
