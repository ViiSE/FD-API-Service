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

package ru.fd.api.service.repository.decorative;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.repository.ProductsRepositoryDecorative;

@Service("productsRepositoryDecorativeProducer")
public class ProductsRepositoryDecorativeProducer {

    private final ApplicationContext ctx;

    public ProductsRepositoryDecorativeProducer(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    public ProductsRepositoryDecorative<Products> productsRepoWithAttrInstance() {
        return ctx.getBean("productsRepositoryWithAttributes", ProductsRepositoryWithAttributesImpl.class);
    }

    public ProductsRepositoryDecorative<Products> productsRepoWithBalancesInstance() {
        return ctx.getBean("productsRepositoryWithBalances", ProductsRepositoryWithBalancesImpl.class);
    }

    public ProductsRepositoryDecorative<Products> productsRepoWithFullDescInstance() {
        return ctx.getBean("productsRepositoryWithFullDescription", ProductsRepositoryWithFullDescriptionImpl.class);
    }

    public ProductsRepositoryDecorative<Products> productsRepoWithPricesInstance() {
        return ctx.getBean("productsRepositoryWithPrices", ProductsRepositoryWithPricesImpl.class);
    }

    public ProductsRepositoryDecorative<Products> productsRepoWithShortDescInstance() {
        return ctx.getBean("productsRepositoryWithShortDescription", ProductsRepositoryWithShortDescriptionImpl.class);
    }

    public ProductsRepositoryDecorative<Products> productsRepoWithStatsInstance() {
        return ctx.getBean("productsRepositoryWithStatuses", ProductsRepositoryWithStatusesImpl.class);
    }
}
