package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.ProductPojo;
import ru.fd.api.service.data.ProductsPojo;

import java.util.List;
import java.util.stream.Collectors;

@Component("productsDefault")
@Scope("prototype")
public class ProductsDefaultImpl implements Products {

    private final List<Product> products;

    public ProductsDefaultImpl(List<Product> products) {
        this.products = products;
    }

    @Override
    public Object formForSend() {
        List<ProductPojo> productPojos = products.stream()
                .map(product -> (ProductPojo) product.formForSend())
                .collect(Collectors.toList());
        return new ProductsPojo(productPojos);
    }
}
