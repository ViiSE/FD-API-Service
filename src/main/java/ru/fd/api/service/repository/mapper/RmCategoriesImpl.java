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

package ru.fd.api.service.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.fd.api.service.entity.Categories;
import ru.fd.api.service.entity.Category;
import ru.fd.api.service.producer.entity.CategoriesProducer;
import ru.fd.api.service.producer.entity.CategoryProducer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component("rmCategories")
public class RmCategoriesImpl implements RowMapper<Categories> {

    private final CategoryProducer categoryProducer;
    private final CategoriesProducer categoriesProducer;

    public RmCategoriesImpl(CategoryProducer categoryProducer, CategoriesProducer categoriesProducer) {
        this.categoryProducer = categoryProducer;
        this.categoriesProducer = categoriesProducer;
    }

    @Override
    public Categories mapRow(ResultSet rs, int i) throws SQLException {
        List<Category> categories = new ArrayList<>();
        do {
            String id = rs.getString("catID").trim();
            String name = rs.getString("catName").trim();

            categories.add(categoryProducer.getCategoryInstance(id, name));
        } while(rs.next());
        return categoriesProducer.getCategoriesInstance(categories);
    }
}
