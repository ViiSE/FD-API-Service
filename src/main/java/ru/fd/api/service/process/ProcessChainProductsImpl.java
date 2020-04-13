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

@Component("processChainProducts")
public class ProcessChainProductsImpl implements ProcessesChain<Products, List<String>> {

    private final ProductsRepository simpleRepo;
    private final Repositories<ProductsRepositoryDecorative> repos;

    public ProcessChainProductsImpl(
            @Qualifier("productsRepositorySimple") ProductsRepository simpleRepo,
            Repositories<ProductsRepositoryDecorative> repos) {
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
