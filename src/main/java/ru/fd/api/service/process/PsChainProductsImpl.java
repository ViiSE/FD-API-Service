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
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.repository.ProductsRepository;
import ru.fd.api.service.repository.ProductsRepositoryDecorative;
import ru.fd.api.service.repository.decorative.Repositories;

import java.util.List;

@Component("psChainProducts")
public class PsChainProductsImpl implements Process<Products, List<String>> {

    private final ProductsRepository simpleRepo;
    private final Repositories<ProductsRepositoryDecorative<Products>> repos;

    public PsChainProductsImpl(
            @Qualifier("productsRepositorySimple") ProductsRepository simpleRepo,
            Repositories<ProductsRepositoryDecorative<Products>> repos) {
        this.simpleRepo = simpleRepo;
        this.repos = repos;
    }

    @Override
    public Products answer(List<String> with) throws ProcessException {
        try {
            Products products = simpleRepo.read();

            for(String param: with)
                if(repos.repo(param) != null)
                    products = repos.repo(param).read(products);

            return products;
        } catch (RepositoryException ex) {
            throw new ProcessException(ex.getMessage(), ex);
        }
    }
}
