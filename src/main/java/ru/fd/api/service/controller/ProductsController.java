package ru.fd.api.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.fd.api.service.creator.ProductsCreator;
import ru.fd.api.service.data.ProductsPojo;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.log.LoggerService;
import ru.fd.api.service.producer.entity.BalanceProducer;
import ru.fd.api.service.producer.entity.BalancesProducer;
import ru.fd.api.service.producer.entity.PriceProducer;
import ru.fd.api.service.producer.entity.PricesProducer;
import ru.fd.api.service.ProductsService;
import ru.fd.api.service.SQLQueryCreatorService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductsController {

    @Autowired private ProductsService productsService;
    @Autowired private SQLQueryCreatorService sqlQueryCreatorService;
    @Autowired private BalanceProducer balanceProducer;
    @Autowired private BalancesProducer balancesProducer;
    @Autowired private PriceProducer priceProducer;
    @Autowired private PricesProducer pricesProducer;

    @Autowired private LoggerService logger;

    @GetMapping("/products")
    @ResponseBody
    public ProductsPojo products(@RequestParam(required = false) List<String> with) {
        try {
            if(with == null)
                with = new ArrayList<>();
            ProductsCreator productsCreator = productsService.productsCreatorProducer()
                    .getProductsCreatorDefaultInstance(
                            productsService.productsRepositoryProcessorsProducer()
                                    .getProductsRepositoryProcessorsSingletonImpl(
                                            productsService.productsRepositoryProducer(),
                                            productsService.productProducer(),
                                            sqlQueryCreatorService.sqlQueryCreatorFromFileString(),
                                            balanceProducer,
                                            balancesProducer,
                                            priceProducer,
                                            pricesProducer),
                            with);
            ProductsPojo productsPojo = (ProductsPojo) productsCreator.create().formForSend();
            logger.info(ProductsController.class, "Site request products with " + with.toString());
            return productsPojo;
        } catch (CreatorException ex) {
            logger.error(ProductsController.class, ex.getMessage() + " <CAUSE>: " + ex.getCause());
            return new ProductsPojo(new ArrayList<>());
        }
    }
}
