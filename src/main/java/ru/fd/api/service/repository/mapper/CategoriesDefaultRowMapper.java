package ru.fd.api.service.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.fd.api.service.entity.Categories;
import ru.fd.api.service.entity.Category;
import ru.fd.api.service.producer.entity.CategoriesProducer;
import ru.fd.api.service.producer.entity.CategoryProducer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDefaultRowMapper implements RowMapper<Categories> {

    private final CategoryProducer categoryProducer;
    private final CategoriesProducer categoriesProducer;

    public CategoriesDefaultRowMapper(CategoryProducer categoryProducer, CategoriesProducer categoriesProducer) {
        this.categoryProducer = categoryProducer;
        this.categoriesProducer = categoriesProducer;
    }

    @Override
    public Categories mapRow(ResultSet rs, int i) throws SQLException {
        List<Category> categories = new ArrayList<>();
        do {
            String id = rs.getString("catID").trim();
            String name = rs.getString("catName").trim();

            categories.add(categoryProducer.getCategoryDefaultInstance(id, name));
        } while(rs.next());
        return categoriesProducer.getCategoriesDefaultInstance(categories);
    }
}
