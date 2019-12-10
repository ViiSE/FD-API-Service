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

package ru.fd.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fd.api.service.producer.creator.CategoriesCreatorProducer;
import ru.fd.api.service.producer.entity.CategoriesProducer;
import ru.fd.api.service.producer.entity.CategoryProducer;
import ru.fd.api.service.producer.repository.CategoriesRepositoryProducer;

@Service("categoriesServiceDefault")
public class CategoriesServiceDefaultImpl implements CategoriesService {

    @Autowired private CategoriesCreatorProducer categoriesCreatorProducer;
    @Autowired private CategoriesRepositoryProducer categoriesRepositoryProducer;
    @Autowired private CategoryProducer categoryProducer;
    @Autowired private CategoriesProducer categoriesProducer;

    @Override
    public CategoriesCreatorProducer categoriesCreatorProducer() {
        return categoriesCreatorProducer;
    }

    @Override
    public CategoriesRepositoryProducer categoriesRepositoryProducer() {
        return categoriesRepositoryProducer;
    }

    @Override
    public CategoryProducer categoryProducer() {
        return categoryProducer;
    }

    @Override
    public CategoriesProducer categoriesProducer() {
        return categoriesProducer;
    }
}
