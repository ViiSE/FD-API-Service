package ru.fd.api.service.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.fd.api.service.ProductsService;
import ru.fd.api.service.SQLQueryCreatorService;
import ru.fd.api.service.creator.ProductsCreator;
import ru.fd.api.service.data.ProductsPojo;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.log.LoggerService;
import ru.fd.api.service.producer.entity.BalanceProducer;
import ru.fd.api.service.producer.entity.BalancesProducer;
import ru.fd.api.service.producer.entity.PriceProducer;
import ru.fd.api.service.producer.entity.PricesProducer;

import java.util.ArrayList;
import java.util.List;

@Api(tags= "Products Controller", description = "Контроллер точек для работы с товарами")
@Controller
public class ProductsController {

    private final ProductsService productsService;
    private final SQLQueryCreatorService sqlQueryCreatorService;
    private final BalanceProducer balanceProducer;
    private final BalancesProducer balancesProducer;
    private final PriceProducer priceProducer;
    private final PricesProducer pricesProducer;

    private final LoggerService logger;

    public ProductsController(
            ProductsService productsService,
            SQLQueryCreatorService sqlQueryCreatorService,
            BalanceProducer balanceProducer,
            BalancesProducer balancesProducer,
            PriceProducer priceProducer,
            PricesProducer pricesProducer,
            LoggerService logger) {
        this.productsService = productsService;
        this.sqlQueryCreatorService = sqlQueryCreatorService;
        this.balanceProducer = balanceProducer;
        this.balancesProducer = balancesProducer;
        this.priceProducer = priceProducer;
        this.pricesProducer = pricesProducer;
        this.logger = logger;
    }

    @ApiOperation(value = "Выгружает все товары")
    @GetMapping("/products")
    @ResponseBody
    public ProductsPojo products(
            @ApiParam(value = "При указании данного параметра товары будут выгружаться с указанными зависимостями.\n" +
                    "Доступные зависимости:" +
                    "\n<span style=\"margin-left:2em\"><b>balances</b> - остатки товара," +
                    "\n<span style=\"margin-left:2em\"><b>prices</b> - цены товара,</span>" +
                    "\n<span style=\"margin-left:2em\"><b>statuses</b> - статусы товара,</span>" +
                    "\n<span style=\"margin-left:2em\"><b>attributes</b> - атрибуты товара</span>" +
                    "\nНесуществующие зависимости, указанные в запросе, будут проигнорированы.\n___" +
                    "\n<i>Примечание: Выгружаемый товар без зависимости</i> <b>prices</b> <i>может иметь нулевую цену.</i>")
            @RequestParam(required = false) List<String> with) {
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
