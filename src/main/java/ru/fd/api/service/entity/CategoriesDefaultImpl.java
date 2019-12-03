package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.BalancePojo;
import ru.fd.api.service.data.BalancesPojo;
import ru.fd.api.service.data.CategoriesPojo;
import ru.fd.api.service.data.CategoryPojo;

import java.util.List;
import java.util.stream.Collectors;

@Component("categoriesDefault")
@Scope("prototype")
public class CategoriesDefaultImpl implements Balances {

    private final List<Category> categories;

    public CategoriesDefaultImpl(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public Object formForSend() {
        List<CategoryPojo> categoryPojos = categories.stream()
                .map(category -> (CategoryPojo) category.formForSend())
                .collect(Collectors.toList());
        return new CategoriesPojo(categoryPojos);
    }
}
