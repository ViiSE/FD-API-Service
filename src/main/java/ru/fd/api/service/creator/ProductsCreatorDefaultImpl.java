package ru.fd.api.service.creator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.repository.ProductsRepository;
import ru.fd.api.service.repository.processor.ProductsRepositoryProcessors;

import java.util.List;

@Service("productsCreatorDefault")
@Scope("prototype")
public class ProductsCreatorDefaultImpl implements ProductsCreator {

    private final ProductsRepositoryProcessors prodsReposPrc;
    private final List<String> with;

    public ProductsCreatorDefaultImpl(ProductsRepositoryProcessors prodsReposPrc, List<String> with) {
        this.prodsReposPrc = prodsReposPrc;
        this.with = with;
    }

    @Override
    public Products create() throws CreatorException {
        try {
            ProductsRepository prRepo = prodsReposPrc.processor("simple").apply(null);

            for(String param: with)
                prRepo = prodsReposPrc.processor(param).apply(prRepo);

            return prRepo.readProducts();
        } catch (RepositoryException ex) {
            throw new CreatorException(ex.getMessage(), ex.getCause());
        }
    }
}
