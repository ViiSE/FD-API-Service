package ru.fd.api.service.repository.decorative;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.repository.ProductsRepositoryDecorative;

@Service("productsRepositoryDecorativeProducer")
public class ProductsRepositoryDecorativeProducer {

    private final ApplicationContext ctx;

    public ProductsRepositoryDecorativeProducer(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    public ProductsRepositoryDecorative productsRepoWithAttrInstance() {
        return ctx.getBean("productsRepositoryWithAttributes", ProductsRepositoryDecorative.class);
    }

    public ProductsRepositoryDecorative productsRepoWithBalancesInstance() {
        return ctx.getBean("productsRepositoryWithBalances", ProductsRepositoryDecorative.class);
    }

    public ProductsRepositoryDecorative productsRepoWithFullDescInstance() {
        return ctx.getBean("productsRepositoryWithFullDescription", ProductsRepositoryDecorative.class);
    }

    public ProductsRepositoryDecorative productsRepoWithPricesInstance() {
        return ctx.getBean("productsRepositoryWithPrices", ProductsRepositoryDecorative.class);
    }

    public ProductsRepositoryDecorative productsRepoWithShortDescInstance() {
        return ctx.getBean("productsRepositoryWithShortDescription", ProductsRepositoryDecorative.class);
    }

    public ProductsRepositoryDecorative productsRepoWithStatsInstance() {
        return ctx.getBean("productsRepositoryWithStatuses", ProductsRepositoryDecorative.class);
    }
}
