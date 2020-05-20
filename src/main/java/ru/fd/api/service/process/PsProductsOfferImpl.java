/*
 * Copyright 2020 ViiSE
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
import ru.fd.api.service.entity.Sendable;
import ru.fd.api.service.exception.ProcessException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.repository.OffersRepository;
import ru.fd.api.service.repository.ProductsRepository;

@Component("psProductsOffer")
public class PsProductsOfferImpl implements Process<Products, Void> {

    private final ProductsRepository productsRepository;

    public PsProductsOfferImpl(@Qualifier("productsOfferRepository") ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public Products answer(Void nothing) throws ProcessException {
        try {
            return productsRepository.read();
        } catch (RepositoryException ex) {
            throw new ProcessException(ex.getMessage(), ex);
        }
    }
}

