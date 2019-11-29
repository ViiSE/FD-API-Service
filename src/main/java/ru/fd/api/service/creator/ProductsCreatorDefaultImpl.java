package ru.fd.api.service.creator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.repository.ProductsRepository;

import java.util.List;

@Component("productsCreatorDefault")
@Scope("prototype")
public class ProductsCreatorDefaultImpl implements ProductsCreator {

    private final List<String> with;

    public ProductsCreatorDefaultImpl(List<String> with) {
        this.with = with;
    }

    @Override
    public Products create() {
//        ProductsRepository repository = productsRepositoryProducer.getProductsRepositoryDefaultInstance(with);
        return null;
    }
}
