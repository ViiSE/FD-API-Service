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
    public Product findProductById(String id) {
        return products.stream()
                .filter(product -> product.id().equals(id))
                .limit(1).collect(Collectors.toList())
                .get(0);
    }

    @Override
    public void decorateProduct(String id, Product product) {
        int index = products.indexOf(findProductById(id));
        products.set(index, product);
    }

    @Override
    public Object formForSend() {
        List<ProductPojo> productPojos = products.stream()
                .map(product -> (ProductPojo) product.formForSend())
                .collect(Collectors.toList());
        return new ProductsPojo(productPojos);
    }
}
