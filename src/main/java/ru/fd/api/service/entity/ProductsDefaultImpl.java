package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.ProductPojo;
import ru.fd.api.service.data.ProductsPojo;
import ru.fd.api.service.producer.entity.ProductProducer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Component("productsDefault")
@Scope("prototype")
public class ProductsDefaultImpl implements Products {

    private final ProductProducer productProducer;
    private final List<Product> products;

    public ProductsDefaultImpl(ProductProducer productProducer, List<Product> products) {
        this.productProducer = productProducer;
        this.products = products;
    }

    @Override
    public Product findProductById(String id) {
        for (Product product : products) {
            if (product.id().equals(id))
                return product;
        }
        return null;
    }

    @Override
    public void decorateProduct(String id, Product product) {
        int index = products.indexOf(findProductById(id));
        products.set(index, product);
    }

    @Override
    public void decorateProduct(int key, Product product) {
        products.set(key, product);
    }

    @Override
    public void removeProducts(Class<? extends Product> decorateProduct) {
        try {
            int key = 0;
            List<Product> decorateProducts = new ArrayList<>();
            for (Product product : products)
                if (Class.forName(decorateProduct.getName()).isInstance(product))
                    decorateProducts.add(productProducer.getProductSimpleInstance(product, key++));
            products.clear();
            products.addAll(decorateProducts);
        } catch (ClassNotFoundException ignore) {}
    }

    @Override
    public Object formForSend() {
        List<ProductPojo> productPojos = products.stream()
                .map(product -> (ProductPojo) product.formForSend())
                .collect(Collectors.toList());
        return new ProductsPojo(productPojos);
    }

    @Override
    public Iterator<Product> iterator() {
        return products.iterator();
    }

    @Override
    public void forEach(Consumer<? super Product> action) {
        products.forEach(action);
    }
}
